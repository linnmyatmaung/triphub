package com.triphub.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triphub.services.*;
import com.google.zxing.WriterException;
import com.triphub.model.Bus;
import com.triphub.model.Passenger;

import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailServiceTest emailService;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/generate-ticket/{passengerId}")
    public String generateETicket(@PathVariable String passengerId, Model model) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        Bus bus = passenger.getBus();

        String qrText = "http://localhost:8080/api/feedback/" + passengerId;
        String qrCodeBase64 = "";

        try {
            qrCodeBase64 = qrCodeService.generateQRCodeBase64(qrText, 250, 250);

            // Decode the Base64 string to bytes and save as an image file
            byte[] imageBytes = Base64.getDecoder().decode(qrCodeBase64);
            String filePath = "public/images/qrCode.png"; // Adjust the path as needed
            try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                fos.write(imageBytes);
            }

            // Send the email with the saved image as an attachment
            emailService.sendEmailWithAttachment("linnmyatmaung03@gmail.com", "Your eTicket", "Please find attached your eTicket.", filePath);

        } catch (WriterException | IOException | MessagingException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to generate and send eTicket");
            return "error";
        }

        return "success";
    }
}
