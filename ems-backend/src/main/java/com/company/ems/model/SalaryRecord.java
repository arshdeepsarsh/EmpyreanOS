package com.company.ems.model;

import jakarta.persistence.*;

@Entity
public class SalaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String employeeName; 
    
    private String salaryMonth; // e.g., "2026-03"
    
    private Double baseSalary;
    private Double bonus;
    private Double deductions;
    private Double netSalary;
    
    private String status; // e.g., "Paid"

    public SalaryRecord() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getSalaryMonth() { return salaryMonth; }
    public void setSalaryMonth(String salaryMonth) { this.salaryMonth = salaryMonth; }

    public Double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(Double baseSalary) { this.baseSalary = baseSalary; }

    public Double getBonus() { return bonus; }
    public void setBonus(Double bonus) { this.bonus = bonus; }

    public Double getDeductions() { return deductions; }
    public void setDeductions(Double deductions) { this.deductions = deductions; }

    public Double getNetSalary() { return netSalary; }
    public void setNetSalary(Double netSalary) { this.netSalary = netSalary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}