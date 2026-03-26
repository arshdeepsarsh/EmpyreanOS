package com.company.ems.controller;

import com.company.ems.model.Employee;
import com.company.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        
        System.out.println("--- NEW LOGIN ATTEMPT ---");
        System.out.println("Email provided: " + email);
        
        // Fetch the user from the database by email
        Employee user = employeeService.getEmployeeByEmail(email);
        
        // 1. Check if user exists
        if (user == null) {
            System.out.println("FAILED: Email not found in database.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        
        // 2. Check if password matches
        if (user.getPassword() == null || !user.getPassword().equals(password)) {            System.out.println("FAILED: Passwords do not match.");
            System.out.println("Database Password: " + user.getPassword() + " | Typed Password: " + password);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        
        // 3. Success!
        System.out.println("SUCCESS: User " + user.getName() + " logged in as " + user.getRole());
        return ResponseEntity.ok(user); 
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        Employee savedUser = employeeService.saveEmployee(employee);
        System.out.println("NEW REGISTRATION: " + savedUser.getEmail() + " as " + savedUser.getRole());
        return ResponseEntity.ok(savedUser);
    }
}