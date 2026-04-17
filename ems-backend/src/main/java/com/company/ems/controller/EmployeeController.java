package com.company.ems.controller;

import com.company.ems.model.Employee;
import com.company.ems.service.EmployeeService;
import com.company.ems.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuditLogService auditService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        Employee savedEmp = employeeService.saveEmployee(employee);
        auditService.logAction("System Admin", "Hired new employee: " + savedEmp.getName(), "Low");
        return savedEmp;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedInfo) {
        Employee existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        // Standard Fields
        existingEmployee.setName(updatedInfo.getName());
        existingEmployee.setEmail(updatedInfo.getEmail());
        existingEmployee.setMobile(updatedInfo.getMobile());
        existingEmployee.setDepartment(updatedInfo.getDepartment());
        existingEmployee.setDesignation(updatedInfo.getDesignation());
        existingEmployee.setRole(updatedInfo.getRole());
        existingEmployee.setJoinDate(updatedInfo.getJoinDate());

        // Sensitive HR Fields
        existingEmployee.setDob(updatedInfo.getDob());
        existingEmployee.setBloodGroup(updatedInfo.getBloodGroup());
        existingEmployee.setFatherName(updatedInfo.getFatherName());
        existingEmployee.setPanCard(updatedInfo.getPanCard());
        existingEmployee.setBankName(updatedInfo.getBankName());
        existingEmployee.setBankAccount(updatedInfo.getBankAccount());
        existingEmployee.setIfscCode(updatedInfo.getIfscCode());
        existingEmployee.setHomeAddress(updatedInfo.getHomeAddress());
        existingEmployee.setStatus(updatedInfo.getStatus());
        
     // --- NEW WORKSPACE FIELDS ---
        existingEmployee.setWorkMode(updatedInfo.getWorkMode());
        existingEmployee.setActiveProjectName(updatedInfo.getActiveProjectName());
        existingEmployee.setProjectProgress(updatedInfo.getProjectProgress());

        Employee savedEmployee = employeeService.saveEmployee(existingEmployee);
        auditService.logAction("System Admin", "Updated profile for: " + savedEmployee.getName(), "Medium");

        return ResponseEntity.ok(savedEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp != null) {
            auditService.logAction("System Admin", "Terminated employee: " + emp.getName(), "CRITICAL");
            employeeService.deleteEmployee(id);
        }
    }
}