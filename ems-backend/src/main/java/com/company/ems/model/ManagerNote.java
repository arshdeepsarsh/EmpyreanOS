package com.company.ems.model;

import jakarta.persistence.*;

@Entity
@Table(name = "manager_notes")
public class ManagerNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String managerName;
    private String employeeName;
    @Column(length = 2000)
    private String noteText;
    private String dateAdded;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getNoteText() { return noteText; }
    public void setNoteText(String noteText) { this.noteText = noteText; }
    public String getDateAdded() { return dateAdded; }
    public void setDateAdded(String dateAdded) { this.dateAdded = dateAdded; }
}