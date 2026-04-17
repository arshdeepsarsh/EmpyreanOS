package com.company.ems.repository;

import com.company.ems.model.LeaveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRecord, Long> {
    List<LeaveRecord> findByEmployeeId(Long employeeId);
    List<LeaveRecord> findAllByOrderByIdDesc();
    List<LeaveRecord> findByEmployeeIdOrderByIdDesc(Long employeeId);
}