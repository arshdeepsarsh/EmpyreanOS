package com.company.ems.model;

import jakarta.persistence.*;

@Entity
public class SalaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String employeeName;
    private String salaryMonth;
    private Double baseSalary;
    private Double bonus;
    
    // The total combined deductions (kept for backward compatibility)
    private Double deductions;
    
    // NEW: Itemized Deductions
    @Column(name = "tax_amount")
    private Double taxAmount = 0.0;

    @Column(name = "pf_amount")
    private Double pfAmount = 0.0;

    @Column(name = "medical_amount")
    private Double medicalAmount = 0.0;

    // NEW: Timestamp for when the payroll was run
    @Column(name = "generated_at")
    private String generatedAt;

    private Double netSalary;
    private String status;

    public SalaryRecord() {}

    // --- Original Getters and Setters ---
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

    // --- NEW Getters and Setters ---
    public Double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }

    public Double getPfAmount() { return pfAmount; }
    public void setPfAmount(Double pfAmount) { this.pfAmount = pfAmount; }

    public Double getMedicalAmount() { return medicalAmount; }
    public void setMedicalAmount(Double medicalAmount) { this.medicalAmount = medicalAmount; }

    public String getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(String generatedAt) { this.generatedAt = generatedAt; }
}