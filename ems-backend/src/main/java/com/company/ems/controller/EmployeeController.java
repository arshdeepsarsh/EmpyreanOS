package com.company.ems.controller;

import com.company.ems.model.Employee;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // 🔥 We are injecting your new PDF Service here!
    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setRole(employeeDetails.getRole());
            employee.setDesignation(employeeDetails.getDesignation());
            if(employeeDetails.getPassword() != null) employee.setPassword(employeeDetails.getPassword());
            if(employeeDetails.getMobile() != null) employee.setMobile(employeeDetails.getMobile());
            return employeeRepository.save(employee);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    // 🔥 THE NEW PDF GENERATION ENDPOINT
    @GetMapping(value = "/{id}/offer-letter", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadOfferLetter(@PathVariable Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        
        // Let the service draw the PDF
        byte[] pdfBytes = pdfGeneratorService.generateOfferLetter(emp);

        // Tell the browser this is a PDF file that should be downloaded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", emp.getName().replace(" ", "_") + "_OfferLetter.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}