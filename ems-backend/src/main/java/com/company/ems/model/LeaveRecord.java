package com.company.ems.model;

import jakarta.persistence.*;

@Entity
public class LeaveRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    
    // We will save the name directly here to make our frontend table easier to build!
    private String employeeName; 
    
    private String startDate;
    private String endDate;
    private String leaveType; // e.g., "Sick", "Casual", "Vacation"
    private String status;    // e.g., "Pending", "Approved", "Rejected"

    public LeaveRecord() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}