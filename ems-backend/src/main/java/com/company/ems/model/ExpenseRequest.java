package com.company.ems.model;

import jakarta.persistence.*;

@Entity
@Table(name = "expense_requests")
public class ExpenseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeName;
    private String description;
    private Double amount;
    private String status;
    private String dateSubmitted;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDateSubmitted() { return dateSubmitted; }
    public void setDateSubmitted(String dateSubmitted) { this.dateSubmitted = dateSubmitted; }
}