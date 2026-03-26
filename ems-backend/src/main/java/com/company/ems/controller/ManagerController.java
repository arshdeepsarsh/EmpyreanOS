package com.company.ems.controller;

import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.company.ems.model.*;
import com.company.ems.repository.*;
import jakarta.annotation.PostConstruct;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teamhub")
public class ManagerController {

    @Autowired private ManagerNoteRepository noteRepo;
    @Autowired private ExpenseRepository expenseRepo;
    
    // 🔥 NEW: We bring in the Asset Repository so Managers can assign hardware!
    @Autowired private AssetRepository assetRepo; 

    // --- NOTES API ---
    @GetMapping("/notes")
    public List<ManagerNote> getNotes(@RequestParam String manager, @RequestParam String employee) {
        return noteRepo.findByManagerNameAndEmployeeNameOrderByIdDesc(manager, employee);
    }

    @PostMapping("/notes")
    public ManagerNote saveNote(@RequestBody ManagerNote note) {
        return noteRepo.save(note);
    }

    // --- EXPENSES API ---
    @GetMapping("/expenses/pending")
    public List<ExpenseRequest> getPendingExpenses() {
        return expenseRepo.findByStatus("Pending");
    }

    @PutMapping("/expenses/{id}/status")
    public ExpenseRequest updateExpenseStatus(@PathVariable Long id, @RequestParam String status) {
        ExpenseRequest req = expenseRepo.findById(id).orElse(null);
        if(req != null) {
            req.setStatus(status);
            return expenseRepo.save(req);
        }
        return null;
    }

    // --- 🔥 NEW: ASSET MANAGER API ---
    @PostMapping("/assets/assign")
    public Asset assignHardware(@RequestBody Asset asset) {
        // Automatically stamp today's date on the new hardware assignment
        asset.setAssignedDate(LocalDate.now().toString());
        asset.setStatus("Active");
        return assetRepo.save(asset);
    }

    // AUTO-SEEDER
    @PostConstruct
    public void seedManagerData() {
        if (expenseRepo.count() == 0) {
            ExpenseRequest e1 = new ExpenseRequest();
            e1.setEmployeeName("Jason Jack"); e1.setDescription("Client Lunch - Acme Corp"); 
            e1.setAmount(145.50); e1.setStatus("Pending"); e1.setDateSubmitted("2026-03-20");
            expenseRepo.save(e1);
        }
        if (noteRepo.count() == 0) {
            ManagerNote n1 = new ManagerNote();
            n1.setManagerName("Reet"); n1.setEmployeeName("Jason Jack");
            n1.setNoteText("Jack is doing great with the new frontend designs. Needs to work on his API routing speed.");
            n1.setDateAdded("2026-03-15");
            noteRepo.save(n1);
        }
    }
}