package com.company.ems.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.company.ems.model.AuditLog;
import com.company.ems.repository.AuditLogRepository;
import jakarta.annotation.PostConstruct;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/godmode")
public class GodModeController {

    @Autowired
    private AuditLogRepository auditRepo;

    @GetMapping("/audit")
    public List<AuditLog> getAuditLogs() {
        return auditRepo.findAllByOrderByIdDesc();
    }

    @PostMapping("/audit")
    public AuditLog createLog(@RequestBody AuditLog log) {
        return auditRepo.save(log);
    }

    // 🔥 AUTO-SEEDER: Fills the security log instantly!
    @PostConstruct
    public void seedAuditData() {
        if (auditRepo.count() == 0) {
            AuditLog l1 = new AuditLog(); l1.setActionTaken("System Initialized"); l1.setPerformedBy("SYSTEM"); l1.setTimestamp("2026-03-20 08:00 AM"); l1.setRiskLevel("Low");
            AuditLog l2 = new AuditLog(); l2.setActionTaken("Approved $145.50 Expense"); l2.setPerformedBy("Reet (Manager)"); l2.setTimestamp("2026-03-20 09:15 AM"); l2.setRiskLevel("Medium");
            AuditLog l3 = new AuditLog(); l3.setActionTaken("Failed Login Attempt (Wrong Password)"); l3.setPerformedBy("Unknown IP"); l3.setTimestamp("2026-03-20 10:42 AM"); l3.setRiskLevel("High");
            AuditLog l4 = new AuditLog(); l4.setActionTaken("Changed User Role to BOSS"); l4.setPerformedBy("Arsh"); l4.setTimestamp("2026-03-20 11:05 AM"); l4.setRiskLevel("CRITICAL");
            
            auditRepo.saveAll(List.of(l1, l2, l3, l4));
        }
    }
}