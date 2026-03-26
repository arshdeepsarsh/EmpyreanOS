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

    // Get all leave requests for the Admin table
    @GetMapping
    public List<LeaveRecord> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    // Apply for a new leave
    @PostMapping("/apply")
    public LeaveRecord applyLeave(@RequestBody LeaveRecord leave) {
        return leaveService.applyLeave(leave);
    }

    // Update status (Approve/Reject)
    @PutMapping("/{id}/status")
    public LeaveRecord updateStatus(@PathVariable Long id, @RequestParam String status) {
        return leaveService.updateLeaveStatus(id, status);
    }
}