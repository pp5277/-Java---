package com.example.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.bo.EmpSeat;
import com.example.backend.repository.EmployeeRepository;

@Service
public class MyService {

    @Autowired
    private EmployeeRepository<EmpSeat> employeeRepository;

    @Transactional
    public List<EmpSeat> getEmployeeSeatingInfo() {
        List<EmpSeat> results = employeeRepository.getEmployeeSeatingInfo();
        // 這裡你可以將結果轉換為 Employee 物件
        // 可以使用自定義的邏輯來處理結果
        return results;
    }
}
