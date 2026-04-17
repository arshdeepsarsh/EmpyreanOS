package com.company.ems.model;

import jakarta.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String employeeName;
    private String attendanceDate;
    private String inTime;
    private String outTime;
    private String status;
    private String date;

    public Attendance() {}

    // Sync both date fields before saving to DB
    @PrePersist
    @PreUpdate
    private void syncDateFields() {
        if (this.attendanceDate != null && (this.date == null || this.date.isEmpty())) {
            this.date = this.attendanceDate;
        }
        if (this.date != null && (this.attendanceDate == null || this.attendanceDate.isEmpty())) {
            this.attendanceDate = this.date;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(String attendanceDate) { this.attendanceDate = attendanceDate; }

    public String getInTime() { return inTime; }
    public void setInTime(String inTime) { this.inTime = inTime; }

    public String getOutTime() { return outTime; }
    public void setOutTime(String outTime) { this.outTime = outTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Falls back to attendanceDate if date is null (safety net for legacy data)
    public String getDate() {
        if (date != null && !date.isEmpty()) return date;
        return attendanceDate;
    }
    public void setDate(String date) { this.date = date; }
}