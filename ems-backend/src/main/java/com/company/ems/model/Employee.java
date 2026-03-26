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
    private String joinDate; // To power your hiring chart!

    public Employee() {}

    // GETTERS & SETTERS
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
    
    public String getDesignation() {return designation;}
    public void setDesignation(String designation) {this.designation = designation;}
    
    public String getJoinDate() {return joinDate;}
    public void setJoinDate(String joinDate) {this.joinDate = joinDate;}
}