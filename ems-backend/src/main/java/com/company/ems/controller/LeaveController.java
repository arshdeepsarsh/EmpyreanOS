package com.company.ems.controller;

import com.company.ems.model.LeaveRecord;
import com.company.ems.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping
    public List<LeaveRecord> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @PostMapping("/apply")
    public LeaveRecord applyLeave(@RequestBody LeaveRecord leave) {
        return leaveService.applyLeave(leave);
    }

    @PutMapping("/{id}/status")
    public LeaveRecord updateStatus(@PathVariable Long id, @RequestParam String status) {
        return leaveService.updateLeaveStatus(id, status);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRecord> getLeavesByEmployee(@PathVariable Long employeeId) {
        return leaveService.getLeavesByEmployee(employeeId);
    }
}