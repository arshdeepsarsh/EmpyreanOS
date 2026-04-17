package com.company.ems.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;
    private String name;
    private String email;
    private String mobile;
    private String department;
    private String password;
    private String role;
    private String designation;
    private String joinDate;
    private String status;
    private String dob;
    private String bloodGroup;
    private String fatherName;
    private String panCard;
    private String bankName;
    private String bankAccount;
    private String ifscCode;
    private String homeAddress;
    private String workMode; 
    private String activeProjectName;
    private Integer projectProgress;


    public Employee() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getJoinDate() { return joinDate; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }

    // --- NEW GETTERS AND SETTERS ---
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getPanCard() { return panCard; }
    public void setPanCard(String panCard) { this.panCard = panCard; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getBankAccount() { return bankAccount; }
    public void setBankAccount(String bankAccount) { this.bankAccount = bankAccount; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getHomeAddress() { return homeAddress; }
    public void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
   
    public String getWorkMode() { return workMode; }
    public void setWorkMode(String workMode) { this.workMode = workMode; }

    public String getActiveProjectName() { return activeProjectName; }
    public void setActiveProjectName(String activeProjectName) { this.activeProjectName = activeProjectName; }

    public Integer getProjectProgress() { return projectProgress; }
    public void setProjectProgress(Integer projectProgress) { this.projectProgress = projectProgress; }
}