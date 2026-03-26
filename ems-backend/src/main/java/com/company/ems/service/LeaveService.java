package com.company.ems.service;

import com.company.ems.model.LeaveRecord;
import com.company.ems.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public List<LeaveRecord> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public LeaveRecord applyLeave(LeaveRecord leave) {
        leave.setStatus("Pending"); // Every new request starts as Pending
        return leaveRepository.save(leave);
    }

    // Method for the Admin to Approve or Reject
    public LeaveRecord updateLeaveStatus(Long id, String newStatus) {
        LeaveRecord leave = leaveRepository.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus(newStatus);
            return leaveRepository.save(leave);
        }
        return null;
    }
}