package com.company.ems.model;

import jakarta.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // We store the Employee's ID so we know who this record belongs to
    private Long employeeId; 
    
    // The date the attendance was taken (e.g., "2026-03-18")
    private String attendanceDate; 
    
    // "Present", "Absent", or "Late"
    private String status; 

    // Constructors
    public Attendance() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(String attendanceDate) { this.attendanceDate = attendanceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}