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

        Employee user = employeeService.getEmployeeByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        if (user.getPassword() == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        Employee savedUser = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedUser);
    }
}