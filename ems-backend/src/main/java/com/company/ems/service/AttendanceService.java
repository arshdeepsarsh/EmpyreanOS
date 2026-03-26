package com.company.ems.service;

import com.company.ems.model.Attendance;
import com.company.ems.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    /**
     * Professional Save Logic:
     * Prevents duplicate rows for the same employee on the same date.
     */
    public List<Attendance> saveAllAttendance(List<Attendance> attendanceRecords) {
        for (Attendance newRecord : attendanceRecords) {
            // 1. Fetch all records for this specific date
            List<Attendance> recordsOnDate = attendanceRepository.findByAttendanceDate(newRecord.getAttendanceDate());

            // 2. Check if this specific employee already has a record in that list
            Optional<Attendance> existingRecord = recordsOnDate.stream()
                .filter(a -> a.getEmployeeId().equals(newRecord.getEmployeeId()))
                .findFirst();

            if (existingRecord.isPresent()) {
                // 3. UPDATE: If record exists, change the status instead of creating a new row
                Attendance recordToUpdate = existingRecord.get();
                recordToUpdate.setStatus(newRecord.getStatus());
                attendanceRepository.save(recordToUpdate);
            } else {
                // 4. INSERT: If no record exists for this employee today, save as new
                attendanceRepository.save(newRecord);
            }
        }
        return attendanceRecords;
    }

    // Get attendance for a specific date (Used by your Dashboard and Reports)
    public List<Attendance> getAttendanceByDate(String date) {
        return attendanceRepository.findByAttendanceDate(date);
    }
}