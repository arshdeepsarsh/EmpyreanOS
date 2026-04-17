package com.company.ems.service;

import com.company.ems.model.Attendance;
import com.company.ems.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance clockIn(Long employeeId, String employeeName) {
        String today = LocalDate.now().toString();
        Optional<Attendance> existing = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);

        if (existing.isPresent()) {
            return existing.get();
        }

        Attendance record = new Attendance();
        record.setEmployeeId(employeeId);
        record.setEmployeeName(employeeName);
        record.setAttendanceDate(today);
        record.setInTime(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));
        record.setStatus("Present");
        return attendanceRepository.save(record);
    }

    public Attendance clockOut(Long employeeId) {
        String today = LocalDate.now().toString();
        Optional<Attendance> existing = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);

        if (existing.isEmpty()) {
            return null;
        }

        Attendance record = existing.get();
        String outTimeStr = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
        record.setOutTime(outTimeStr);

        if (record.getInTime() != null) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime inTime = LocalTime.parse(record.getInTime(), fmt);
                LocalTime outTime = LocalTime.parse(outTimeStr, fmt);
                long minutesWorked = Duration.between(inTime, outTime).toMinutes();
                if (minutesWorked < 600) {
                    record.setStatus("Half Day");
                }
            } catch (Exception e) {
                record.setStatus("Half Day");
            }
        }

        return attendanceRepository.save(record);
    }

    public List<Attendance> getByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    public List<Attendance> getByDate(String date) {
        return attendanceRepository.findByAttendanceDate(date);
    }

    public Optional<Attendance> getTodayRecord(Long employeeId) {
        String today = LocalDate.now().toString();
        return attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, today);
    }
}