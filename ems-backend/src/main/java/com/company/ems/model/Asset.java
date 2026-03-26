package com.company.ems.model;

import jakarta.persistence.*;

@Entity
@Table(name = "company_assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeName;
    private String type;
    private String model;
    private String serialNumber;
    private String assignedDate;
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public String getAssignedDate() { return assignedDate; }
    public void setAssignedDate(String assignedDate) { this.assignedDate = assignedDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}