package com.company.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.ems.model.SalaryRecord;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryRecord, Long> {
    
    // 🔥 IDEMPOTENCY QUERY: Checks if a payment already exists for this specific person in this specific month
    boolean existsByEmployeeIdAndSalaryMonth(Long employeeId, String salaryMonth);
    
}