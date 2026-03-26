package com.company.ems.repository;

import com.company.ems.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    
    // Custom magic method to find attendance for a specific day!
    List<Attendance> findByAttendanceDate(String attendanceDate);
}