<template>
  <div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold text-center mb-6">座位管理系統</h1>

    <!-- 員工選擇下拉選單 -->
    <div class="mb-6">
      <label class="block text-gray-700 mb-2">選擇員工:</label>
      <select 
        v-model="selectedEmployee"
        class="border border-gray-300 rounded p-2 w-full max-w-md"
      >
        <option value="">-- 請選擇員工 --</option>
        <option 
          v-for="emp in employees" 
          :key="emp.empId" 
          :value="emp"
        >
          {{ emp.name }} ({{ emp.empId }})
        </option>
      </select>
    </div>

    <!-- 4x4 座位表 -->
    <div class="mb-6 space-y-4">
      <div 
        v-for="(row, rowIndex) in seats" 
        :key="rowIndex" 
        class="grid grid-cols-4 gap-4"
      >
        <button
          v-for="seat in row"
          :key="seat.id"
          :class="[
            'p-4 border rounded h-24 flex flex-col items-center justify-center transition-colors',
            getSeatClass(seat)
          ]"
          @click="selectSeat(seat)"
        >
          <div>{{ seat.id }}</div>
          <div v-if="seat.employee" class="text-sm mt-1">{{ seat.employee.name }}</div>
          <div v-else class="text-sm mt-1">空座位</div>
        </button>
      </div>
    </div>

    <!-- 當前選擇的座位和員工信息 -->
    <div v-if="selectedSeat || selectedEmployee" class="mb-6 p-4 border rounded bg-gray-50">
      <h2 class="font-bold mb-2">當前選擇:</h2>
      <div v-if="selectedSeat">座位: {{ selectedSeat.id }}</div>
      <div v-if="selectedEmployee">員工: {{ selectedEmployee.name }} ({{ selectedEmployee.empId }})</div>
    </div>

    <!-- 提交按鈕 -->
    <button 
      @click="submitSeatAssignment"
      class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded"
      :disabled="!canSubmit"
    >
      分配座位
    </button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      employees: [],
      seatInfo: [],
      selectedEmployee: null,
      selectedSeat: null,
      seats: [],
    }
  },
  computed: {
    canSubmit() {
      return this.selectedEmployee && this.selectedSeat;
    }
  },
  created() {
    this.initializeSeats();
    this.fetchEmployees();
    this.fetchSeatInfo();
  },
  methods: {
    initializeSeats() {
      this.seats = [];
      for (let i = 1; i <= 4; i++) {
        const row = [];
        for (let j = 1; j <= 4; j++) {
          row.push({
            id: `${i}樓:座位${j}`,
            row: i,
            col: j,
            employee: null
          });
        }
        this.seats.push(row);
      }
    },

    // 取得員工列表
    async fetchEmployees() {
      try {
        const response = await axios.get('http://localhost:8080/api/employees');
        this.employees = response.data;
      } catch (error) {
        console.error('取得員工資訊失敗:', error);
        this.employees = [];
      }
    },

    // 取得目前座位使用情況
    async fetchSeatInfo() {
      try {
        const response = await axios.get('http://localhost:8080/api/empseat-info');
        this.seatInfo = response.data;
        this.updateSeatsWithEmployees();
      } catch (error) {
        console.error('取得座位資料失敗:', error);
        this.seatInfo = [];
      }
    },

    // 更新座位資料與員工對應
    updateSeatsWithEmployees() {
      for (const seat of this.seats.flat()) {
        seat.employee = null; // 清除舊的
      }

      for (const info of this.seatInfo) {
        const row = info.row;
        const col = info.col;
        if (row >= 1 && row <= 4 && col >= 1 && col <= 4) {
          const seat = this.seats[row - 1][col - 1];
          seat.employee = info;
        }
      }
    },

    selectSeat(seat) {
      this.selectedSeat = seat;
    },

    getSeatClass(seat) {
      if (seat.employee) {
        return 'bg-green-200 hover:bg-green-300 border-green-300';
      } else if (this.selectedSeat && this.selectedSeat.id === seat.id) {
        return 'bg-blue-200 hover:bg-blue-300 border-blue-300';
      } else {
        return 'bg-white hover:bg-gray-100 border-gray-300';
      }
    },

    async submitSeatAssignment() {
      if (!this.selectedEmployee || !this.selectedSeat) {
        alert('請選擇員工與座位');
        return;
      }

      const rowCol = this.selectedSeat.id.split('-');
      const row = parseInt(rowCol[0]);
      const col = parseInt(rowCol[1]);

      const payload = {
        employeeId: this.selectedEmployee.empId,
        row,
        col
      };

      try {
        await axios.post('http://localhost:8080/api/seatchart', payload);
        alert('座位分配成功!');
        this.fetchSeatInfo();
        this.selectedEmployee = null;
        this.selectedSeat = null;
      } catch (error) {
        console.error('提交座位分配失敗:', error);
        alert('座位分配失敗，請稍後再試');
      }
    }
  }
}
</script>
