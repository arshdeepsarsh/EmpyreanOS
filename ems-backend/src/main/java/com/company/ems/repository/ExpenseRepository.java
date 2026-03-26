package com.company.ems.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.ems.model.ExpenseRequest;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseRequest, Long> {
    List<ExpenseRequest> findByStatus(String status);
    
    // 🔥 NEW: Let employees look up their own expense history!
    List<ExpenseRequest> findByEmployeeNameOrderByIdDesc(String employeeName);
}