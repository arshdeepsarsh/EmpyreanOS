package com.company.ems.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import com.company.ems.model.Employee;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfGeneratorService {

    public byte[] generateOfferLetter(Employee employee) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Create a standard A4 Document
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();

        // Set up our fonts
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Color.DARK_GRAY);
        Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.BLACK);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.DARK_GRAY);
        Font highlightFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);

        // Build the Document
        Paragraph header = new Paragraph("EMPYREAN OS - OFFICIAL OFFER OF EMPLOYMENT", titleFont);
        header.setAlignment(Paragraph.ALIGN_CENTER);
        header.setSpacingAfter(30);
        document.add(header);

        Paragraph greeting = new Paragraph("Dear " + employee.getName() + ",", subTitleFont);
        greeting.setSpacingAfter(15);
        document.add(greeting);

        Paragraph p1 = new Paragraph("We are thrilled to officially offer you the position of ", bodyFont);
        p1.add(new Paragraph(employee.getDesignation() + " within our " + employee.getDepartment() + " department.", highlightFont));
        p1.setSpacingAfter(10);
        document.add(p1);

        Paragraph p2 = new Paragraph("At Empyrean, we pride ourselves on pushing the boundaries of technology and business operations. Based on your impressive background, we believe you will be a tremendous asset to our team. As a " + employee.getRole() + " level user, you will be expected to uphold our company values and drive our mission forward.", bodyFont);
        p2.setSpacingAfter(10);
        document.add(p2);
        
        Paragraph p3 = new Paragraph("Your official start date is recorded as: ", bodyFont);
        p3.add(new Paragraph(employee.getJoinDate() + ".", highlightFont));
        p3.setSpacingAfter(30);
        document.add(p3);

        Paragraph signature = new Paragraph("Welcome to the team,\n\nArshdeep Singh\nChief Executive Officer\nEmpyreanHR", bodyFont);
        document.add(signature);

        document.close();

        return out.toByteArray();
    }
}