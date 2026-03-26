package com.company.ems.controller;

import com.company.ems.model.SalaryRecord;
import com.company.ems.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/payroll")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public List<SalaryRecord> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @PostMapping("/generate")
    public SalaryRecord generatePayroll(@RequestBody SalaryRecord record) {
        return salaryService.generatePayroll(record);
    }
}