package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.bo.EmpSeat;
import com.example.backend.service.MyService;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    // GET 請求，查詢所有員工座位資訊
    @GetMapping("/employees/seating-info")
    public List<EmpSeat> getEmployeeSeatingInfo() {
        // 呼叫服務層的邏輯，獲取員工座位資訊
        return myService.getEmployeeSeatingInfo();
    }
}
