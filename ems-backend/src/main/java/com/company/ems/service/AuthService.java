package com.company.ems.service;

import com.company.ems.dto.LoginRequest;
import com.company.ems.model.Employee;
import com.company.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean login(LoginRequest request) {

        Optional<Employee> employee = Optional.empty();

        // Try email
        employee = employeeRepository.findFirstByEmail(request.getUsername());

        // Try mobile if not found
        if (employee.isEmpty()) {
            employee = employeeRepository.findByMobile(request.getUsername());
        }

        // Try employeeId if still not found
        if (employee.isEmpty()) {
            employee = employeeRepository.findByEmployeeId(request.getUsername());
        }

        // If still not found
        if (employee.isEmpty()) {
            return false;
        }

        // Match password
        if (employee.get().getPassword().equals(request.getPassword())) {
            return true;
        }

        return false;
    }
}