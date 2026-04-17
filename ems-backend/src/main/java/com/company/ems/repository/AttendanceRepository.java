package com.company.ems.repository;

import com.company.ems.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByAttendanceDate(String attendanceDate);
    List<Attendance> findByEmployeeId(Long employeeId);
    Optional<Attendance> findByEmployeeIdAndAttendanceDate(Long employeeId, String attendanceDate);
}