package com.company.ems.controller;

import com.company.ems.model.Attendance;
import com.company.ems.service.AttendanceService;
import com.company.ems.repository.AttendanceRepository; // <-- Added this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // --- ADDED THIS TO FIX THE ERROR ---
    @Autowired
    private AttendanceRepository attendanceRepository; 

    @PostMapping("/clockin")
    public ResponseEntity<Attendance> clockIn(@RequestBody Map<String, String> body) {
        Long employeeId = Long.parseLong(body.get("employeeId"));
        String employeeName = body.get("employeeName");
        Attendance record = attendanceService.clockIn(employeeId, employeeName);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/clockout")
    public ResponseEntity<Attendance> clockOut(@RequestBody Map<String, String> body) {
        Long employeeId = Long.parseLong(body.get("employeeId"));
        Attendance record = attendanceService.clockOut(employeeId);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }

    @GetMapping("/my/{employeeId}")
    public List<Attendance> getMyHistory(@PathVariable Long employeeId) {
        return attendanceService.getByEmployee(employeeId);
    }

    @GetMapping("/date/{date}")
    public List<Attendance> getByDate(@PathVariable String date) {
        return attendanceService.getByDate(date);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getEmployeeHistory(@PathVariable Long employeeId) {
        return attendanceService.getByEmployee(employeeId);
    }

    @GetMapping("/today/{employeeId}")
    public ResponseEntity<Attendance> getTodayRecord(@PathVariable Long employeeId) {
        Optional<Attendance> record = attendanceService.getTodayRecord(employeeId);
        return record.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }
    
    // --- THIS WILL NOW WORK PERFECTLY ---
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}