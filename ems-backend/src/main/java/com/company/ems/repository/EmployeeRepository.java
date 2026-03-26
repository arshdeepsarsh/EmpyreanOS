package com.company.ems.repository;

import com.company.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Add the word "First" here!
    Optional<Employee> findFirstByEmail(String email);

    Optional<Employee> findByMobile(String mobile);

    Optional<Employee> findByEmployeeId(String employeeId);
}