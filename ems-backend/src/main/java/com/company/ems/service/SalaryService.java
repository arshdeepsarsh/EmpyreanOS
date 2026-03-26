package com.company.ems.service;

import com.company.ems.model.SalaryRecord;
import com.company.ems.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    public List<SalaryRecord> getAllSalaries() {
        return salaryRepository.findAll();
    }

    public SalaryRecord generatePayroll(SalaryRecord record) {
        // Automatically calculate the Net Salary!
        double net = record.getBaseSalary() + record.getBonus() - record.getDeductions();
        record.setNetSalary(net);
        record.setStatus("Paid"); 
        
        return salaryRepository.save(record);
    }
}