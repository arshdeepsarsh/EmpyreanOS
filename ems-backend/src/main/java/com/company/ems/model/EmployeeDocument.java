package com.company.ems.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_docs")
public class EmployeeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeName;
    private String title;
    private String type; // e.g., PDF, DOCX
    private String addedDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getAddedDate() { return addedDate; }
    public void setAddedDate(String addedDate) { this.addedDate = addedDate; }
}