package com.company.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.ems.model.SalaryRecord;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryRecord, Long> {
    
    // 🔥 THE MAGIC QUERY: This teaches Spring Boot how to look into the database 
    // to see if a payment already exists for this person in this month!
    boolean existsByEmployeeIdAndSalaryMonth(Long employeeId, String salaryMonth);
    
}