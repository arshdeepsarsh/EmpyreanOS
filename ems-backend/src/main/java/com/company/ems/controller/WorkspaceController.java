package com.company.ems.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.company.ems.model.Asset;
import com.company.ems.model.EmployeeDocument;
import com.company.ems.model.ExpenseRequest;
import com.company.ems.repository.AssetRepository;
import com.company.ems.repository.EmployeeDocumentRepository;
import com.company.ems.repository.ExpenseRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    @Autowired private AssetRepository assetRepo;
    @Autowired private EmployeeDocumentRepository docRepo;
    
    // 🔥 1. Add the Expense Database Connection
    @Autowired private ExpenseRepository expenseRepo; 

    @GetMapping("/assets/{employeeName}")
    public List<Asset> getAssets(@PathVariable String employeeName) { return assetRepo.findByEmployeeName(employeeName); }

    @GetMapping("/docs/{employeeName}")
    public List<EmployeeDocument> getDocs(@PathVariable String employeeName) { return docRepo.findByEmployeeName(employeeName); }

    // 🔥 2. API to let employees see their own past expenses
    @GetMapping("/expenses/{employeeName}")
    public List<ExpenseRequest> getMyExpenses(@PathVariable String employeeName) {
        return expenseRepo.findByEmployeeNameOrderByIdDesc(employeeName);
    }

    // 🔥 3. API to submit a brand new expense!
    @PostMapping("/expenses")
    public ExpenseRequest submitExpense(@RequestBody ExpenseRequest request) {
        request.setStatus("Pending"); // Always starts as Pending!
        request.setDateSubmitted(LocalDate.now().toString());
        return expenseRepo.save(request);
    }

    @PostConstruct
    public void seedTestData() {
        if (assetRepo.count() == 0) {
            Asset a1 = new Asset(); a1.setEmployeeName("Arsh"); a1.setType("Laptop"); a1.setModel("MacBook Pro 16\" M3 Max"); a1.setSerialNumber("C02-ARSH-99X"); a1.setAssignedDate("2024-01-10"); a1.setStatus("Active");
            Asset a3 = new Asset(); a3.setEmployeeName("Jason Jack"); a3.setType("Laptop"); a3.setModel("ThinkPad X1 Carbon"); a3.setSerialNumber("TP-8812-ZZ"); a3.setAssignedDate("2024-02-01"); a3.setStatus("Active");
            assetRepo.saveAll(List.of(a1, a3));
        }
        if (docRepo.count() == 0) {
            EmployeeDocument d1 = new EmployeeDocument(); d1.setEmployeeName("Arsh"); d1.setTitle("CEO Founder Agreement"); d1.setType("PDF"); d1.setAddedDate("2024-01-01");
            EmployeeDocument d3 = new EmployeeDocument(); d3.setEmployeeName("Jason Jack"); d3.setTitle("Signed Offer Letter"); d3.setType("PDF"); d3.setAddedDate("2024-02-01");
            docRepo.saveAll(List.of(d1, d3));
        }
    }
}