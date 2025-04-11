<template>
  <div class="container mx-auto p-6 max-w-6xl">
    <h1 class="text-4xl font-extrabold text-center mb-8 text-gray-800">座位管理系統</h1>

    <div class="mb-6">
      <label class="block text-lg font-medium text-gray-700 mb-2">選擇員工:</label>
      <select 
        v-model="selectedEmployee"
        class="border border-gray-300 rounded px-4 py-2 w-full max-w-md"
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

    <div class="mb-8 space-y-4">
      <div 
        v-for="(row, rowIndex) in seats" 
        :key="rowIndex" 
        class="grid grid-cols-4 gap-4 justify-items-center"
      >
        <button
          v-for="seat in row"
          :key="seat.id"
          class="w-88 h-28 min-w-[11rem] min-h-[7rem] border rounded-lg shadow-sm text-sm text-center font-medium flex flex-col justify-center items-center whitespace-pre-line transition-all"
          :class="getSeatClass(seat)"
          @click="selectSeat(seat)"
          :disabled="seat.employee !== null"
        >
          {{ seat.id }}<br>
          <span class="text-xs text-gray-600">
            <template v-if="seat.employee">[員編:{{ seat.employee.empId }}]</template>
            <template v-else>空座位</template>
          </span>
        </button>
      </div>
    </div>

    <div v-if="selectedSeat || selectedEmployee" class="mb-6 p-4 border rounded bg-gray-100">
      <h2 class="font-bold mb-2 text-gray-800">當前選擇:</h2>
      <div v-if="selectedSeat">座位: {{ selectedSeat.id }}</div>
      <div v-if="selectedEmployee">員工: {{ selectedEmployee.name }} ({{ selectedEmployee.empId }})</div>
    </div>

    <div class="flex space-x-4">
      <button 
        @click="submitSeatAssignment"
        class="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-6 rounded disabled:opacity-50 disabled:cursor-not-allowed"
        :disabled="!canSubmit"
      >
        分配座位
      </button>

      <button 
        @click="cancelSeatAssignment"
        class="bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-6 rounded disabled:opacity-50 disabled:cursor-not-allowed"
        :disabled="!selectedEmployee"
      >
        取消分配
      </button>
    </div>
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

    async fetchEmployees() {
      try {
        const response = await axios.get('http://localhost:8080/api/employees');
        this.employees = response.data;
      } catch (error) {
        console.error('取得員工資訊失敗:', error);
        this.employees = [];
      }
    },

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

    updateSeatsWithEmployees() {
      for (const seat of this.seats.flat()) {
        seat.employee = null;
      }

      for (const employee of this.employees) {
        if (employee.seatingChart?.floorNo && employee.seatingChart?.seatNo) {
          const floorNo = employee.seatingChart.floorNo;
          const seatNo = employee.seatingChart.seatNo;

          if (floorNo >= 1 && floorNo <= 4 && seatNo >= 1 && seatNo <= 4) {
            const seat = this.seats[floorNo - 1][seatNo - 1];
            seat.employee = employee;
          }
        }
      }
    },

    selectSeat(seat) {
      if (seat.employee !== null) return;
      this.selectedSeat = seat;
    },

    getSeatClass(seat) {
      if (seat.employee) {
        return 'bg-red-200 text-red-800 cursor-not-allowed';
      } else if (this.selectedSeat && this.selectedSeat.id === seat.id) {
        return 'bg-green-200 border-green-300';
      } else {
        return 'bg-white hover:bg-gray-100 border-gray-300';
      }
    },

    async submitSeatAssignment() {
      if (!this.selectedEmployee || !this.selectedSeat) {
        alert('請選擇員工與座位');
        return;
      }

      const floorSeatSeq = this.selectedEmployee?.seatingChart?.floorSeatSeq;

      if (!floorSeatSeq) {
        alert('找不到該員工的 floorSeatSeq');
        return;
      }

      const payload = {
        floorSeatSeq: floorSeatSeq,
        floorNo: this.selectedSeat.row,
        seatNo: this.selectedSeat.col
      };

      try {
        await axios.post('http://localhost:8080/api/seatchart', payload);
        alert('座位分配成功!');
        await this.fetchEmployees();
        this.fetchSeatInfo();
        this.selectedEmployee = null;
        this.selectedSeat = null;
      } catch (error) {
        console.error('提交座位分配失敗:', error);
        alert('座位分配失敗，請稍後再試');
      }
    },

    async cancelSeatAssignment() {
      if (!this.selectedEmployee) {
        alert('請選擇員工');
        return;
      }

      const floorSeatSeq = this.selectedEmployee?.seatingChart?.floorSeatSeq;

      if (!floorSeatSeq) {
        alert('找不到該員工的 floorSeatSeq');
        return;
      }

      try {
        await axios.post('http://localhost:8080/api/cancelseatchart', { floorSeatSeq });
        alert('座位取消成功!');
        await this.fetchEmployees();
        this.fetchSeatInfo();
        this.selectedEmployee = null;
        this.selectedSeat = null;
      } catch (error) {
        console.error('取消座位分配失敗:', error);
        alert('取消座位失敗，請稍後再試');
      }
    }
  }
}
</script>
