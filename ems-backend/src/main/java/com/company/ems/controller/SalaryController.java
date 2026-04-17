package com.company.ems.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.ems.model.SalaryRecord;
import com.company.ems.model.Attendance;
import com.company.ems.model.LeaveRecord;
import com.company.ems.repository.SalaryRepository;
import com.company.ems.repository.AttendanceRepository;
import com.company.ems.repository.LeaveRepository;
import com.company.ems.service.AuditLogService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryRepository salaryRepo;

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private LeaveRepository leaveRepo;

    @Autowired
    private AuditLogService auditService;

    @GetMapping
    public List<SalaryRecord> getAllPayroll() {
        return salaryRepo.findAll();
    }

    @PostMapping("/draft")
    public ResponseEntity<?> draftPayroll(@RequestBody Map<String, Object> request) {
        try {
            Long employeeId = Long.valueOf(request.get("employeeId").toString());
            String salaryMonth = request.get("salaryMonth").toString(); 
            Double baseSalary = Double.valueOf(request.get("baseSalary").toString());

            int standardWorkingDays = 22; 
            double payableDays = 0.0;

            List<Attendance> allAttendance = attendanceRepo.findAll();
            for (Attendance a : allAttendance) {
                if (a.getEmployeeId() != null && a.getEmployeeId().equals(employeeId)) {
                    // Use attendanceDate (the field populated by clockIn), with fallback to date
                    String attDate = a.getAttendanceDate();
                    if (attDate == null || attDate.isEmpty()) {
                        attDate = a.getDate();
                    }
                    if (attDate == null || attDate.isEmpty()) {
                        continue; // Skip if no date at all
                    }
                    
                    if (attDate != null && attDate.startsWith(salaryMonth)) {
                        if ("Present".equalsIgnoreCase(a.getStatus())) {
                            payableDays += 1.0;
                        } else if ("Half Day".equalsIgnoreCase(a.getStatus()) || "Half-Day".equalsIgnoreCase(a.getStatus())) {
                            payableDays += 0.5;
                        }
                    }
                }
            }

            List<LeaveRecord> allLeaves = leaveRepo.findAll();
            for (LeaveRecord l : allLeaves) {
                if (l.getEmployeeId() != null && l.getEmployeeId().equals(employeeId)) {
                    if ("Approved".equalsIgnoreCase(l.getStatus()) && l.getStartDate() != null && l.getStartDate().startsWith(salaryMonth)) {
                        payableDays += 1.0; 
                    }
                }
            }

            double proratedSalary = (baseSalary / standardWorkingDays) * payableDays;
            
            if (proratedSalary > baseSalary) {
                proratedSalary = baseSalary;
            }

            double estimatedTax = 0.0;
            if (proratedSalary > 5000) { 
                estimatedTax = proratedSalary * 0.10; 
            }
            double estimatedPF = proratedSalary * 0.12;  

            Map<String, Object> draftResult = new HashMap<>();
            draftResult.put("payableDays", payableDays);
            draftResult.put("calculatedBase", Math.round(proratedSalary * 100.0) / 100.0);
            draftResult.put("suggestedTax", Math.round(estimatedTax * 100.0) / 100.0);
            draftResult.put("suggestedPF", Math.round(estimatedPF * 100.0) / 100.0);

            return ResponseEntity.ok(draftResult);

        } catch (Exception e) {
            e.printStackTrace(); // Print full error to console for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"Failed to calculate: " + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generatePayroll(@RequestBody SalaryRecord record) {
        boolean alreadyPaid = salaryRepo.existsByEmployeeIdAndSalaryMonth(record.getEmployeeId(), record.getSalaryMonth());

        if (alreadyPaid) {
            auditService.logAction("System Guard", "Blocked duplicate payroll attempt for " + record.getEmployeeName(), "CRITICAL");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("{\"error\": \"IDEMPOTENCY_LOCKED: Double payment prevented.\"}");
        }

        double base = (record.getBaseSalary() != null) ? record.getBaseSalary() : 0.0;
        double bonus = (record.getBonus() != null) ? record.getBonus() : 0.0;
        double tax = (record.getTaxAmount() != null) ? record.getTaxAmount() : 0.0;
        double pf = (record.getPfAmount() != null) ? record.getPfAmount() : 0.0;
        double medical = (record.getMedicalAmount() != null) ? record.getMedicalAmount() : 0.0;
        
        double totalDeductions = tax + pf + medical;
        record.setDeductions(totalDeductions);
        record.setNetSalary(base + bonus - totalDeductions);
        record.setStatus("Paid");
        
        if (record.getGeneratedAt() == null || record.getGeneratedAt().isEmpty()) {
            record.setGeneratedAt(Instant.now().toString());
        }

        SalaryRecord saved = salaryRepo.save(record);
        auditService.logAction("Finance Admin", "Generated $" + saved.getNetSalary() + " payroll for " + saved.getEmployeeName() + " (" + saved.getSalaryMonth() + ")", "High");

        return ResponseEntity.ok(saved);
    }
}