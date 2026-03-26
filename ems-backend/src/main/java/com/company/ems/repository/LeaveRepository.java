package com.company.ems.repository;

import com.company.ems.model.LeaveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRecord, Long> {
}