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
        Optional<Employee> employee = employeeRepository.findFirstByEmail(request.getUsername());

        if (employee.isEmpty()) {
            employee = employeeRepository.findByMobile(request.getUsername());
        }

        if (employee.isEmpty()) {
            employee = employeeRepository.findByEmployeeId(request.getUsername());
        }

        if (employee.isEmpty()) {
            return false;
        }

        return employee.get().getPassword().equals(request.getPassword());
    }
}