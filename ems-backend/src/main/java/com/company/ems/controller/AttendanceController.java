package com.company.ems.controller;

import com.company.ems.model.Attendance;
import com.company.ems.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Crucial for connecting to your HTML frontend
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Catches the POST request from your frontend to save the whole list
    @PostMapping("/save")
    public List<Attendance> saveAttendanceRecords(@RequestBody List<Attendance> records) {
        return attendanceService.saveAllAttendance(records);
    }

    // Catches the GET request to load attendance for a specific day
    @GetMapping("/date/{date}")
    public List<Attendance> getAttendanceForDate(@PathVariable String date) {
        return attendanceService.getAttendanceByDate(date);
    }
}