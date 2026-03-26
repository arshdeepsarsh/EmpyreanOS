package com.company.ems.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.ems.model.EmployeeDocument;

@Repository
public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Long> {
    List<EmployeeDocument> findByEmployeeName(String employeeName);
}