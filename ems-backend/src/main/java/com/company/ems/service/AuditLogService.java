package com.company.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.ems.model.AuditLog;
import com.company.ems.repository.AuditLogRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditRepo;

    public void logAction(String user, String action, String riskLevel) {
        AuditLog log = new AuditLog();
        log.setPerformedBy(user);
        log.setActionTaken(action);
        log.setRiskLevel(riskLevel);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        log.setTimestamp(LocalDateTime.now().format(dtf));
        auditRepo.save(log);
    }
}