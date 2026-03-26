package com.company.ems.model;

import jakarta.persistence.*;

@Entity
@Table(name = "system_audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actionTaken;
    private String performedBy;
    private String timestamp;
    private String riskLevel; // Low, Medium, High, CRITICAL

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getActionTaken() { return actionTaken; }
    public void setActionTaken(String actionTaken) { this.actionTaken = actionTaken; }
    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
}