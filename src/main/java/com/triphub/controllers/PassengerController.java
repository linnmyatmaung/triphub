package com.triphub.controllers;

import com.google.zxing.WriterException;
import com.triphub.model.*;
import com.triphub.services.*;
import com.triphub.services.QRCodeService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.FileSystemResource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
@Controller
@RequestMapping("/ap")
public class PassengerController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EmailService emailService;  // Inject the email service
    
    


    @GetMapping("/generate-ticket/{passengerId}")
    public String generateETicket(@PathVariable String passengerId, Model model) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        Bus bus = passenger.getBus();

        String qrText = "http://localhost:8080/api/feedback/" + passengerId;
        String qrCodeBase64 = "";

        // Define the file name using a unique identifier like passengerId
        String fileName = "qr_" + passengerId + ".jpg";
        String tempDir = System.getProperty("java.io.tmpdir");
        String filePath = tempDir + File.separator + fileName;

        try {
            qrCodeBase64 = qrCodeService.generateQRCodeBase64(qrText, 250, 250);
            byte[] imageBytes = Base64.getDecoder().decode(qrCodeBase64);

            File file = new File(filePath);
            if (file.exists()) {
                System.out.println("File exists, attempting to overwrite...");
            } else {
                System.out.println("File does not exist, creating new file...");
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(imageBytes);
                System.out.println("File written successfully: " + filePath);
            }
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to generate QR code");
            System.out.println("Error generating QR code or writing file: " + e.getMessage());
            return "error";
            
        }

        String imageCid = "qrCodeImage";
        String ticketBody = "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Final Bus E-Ticket</title>"
                + "</head>"
                + "<body style='font-family: Arial, sans-serif; background-color: #e9ecef; color: #333; padding: 20px; margin: 0;'>"
                + "<div style='background-color: #ffffff; max-width: 800px; margin: 0 auto; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);'>"
                + "    <div style='text-align: left; margin-bottom: 20px; border-bottom: 2px solid #004085; padding-bottom: 10px;'>"
                + "        <h1 style='font-size: 24px; color: #004085; margin: 0;'>Triphub Travellers Express</h1>"
                + "        <p style='margin: 5px 0; font-size: 18px; color: #6c757d;'>Final Bus E-Ticket</p>"
                + "    </div>"
                + "    <div style='display: flex; justify-content: space-between; margin-bottom: 20px; margin-right:4px;'>"
                + "        <div style='width: 48%;'>"
                + "            <p style='margin: 5px 0; font-size: 14px;'>Name: <span style='font-weight: bold;'>" + passenger.getFullname() + "</span></p>"
                + "            <p style='margin: 5px 0; font-size: 14px;'>NRC No: <span style='font-weight: bold;'>11/AMMN(098228)</span></p>"
                + "            <p style='margin: 5px 0; font-size: 14px;'>Seats: <span style='font-weight: bold;'>1 / Normal</span></p>"
                + "            <p style='margin: 5px 0; font-size: 14px;'>Bus: <span style='font-weight: bold;'>FAMOUS</span></p>"
                + "        </div>"
                + "        <div style='width: 48%;'>"
                + "            <p style='margin: 5px 0; font-size: 14px;'>Triphub Travellers Express</p>"
                + "            <p style='margin: 5px 0; font-size: 14px;'>Contact Phone Number: +959788113366</p>"
                + "            <p style='margin: 5px 0; font-size: 14px;'>Email: triphub@gmail.com</p>"
                + "        </div>"
                + "    </div>"
                + "    <div style='margin-top: 20px; border: 1px solid #dee2e6; border-radius: 5px; overflow: hidden;'>"
                + "        <table style='width: 100%; border-collapse: collapse;'>"
                + "            <tr>"
                + "                <th style='padding: 10px; text-align: center; border-bottom: 1px solid #dee2e6; background-color: #dc3545; color: #ffffff; font-size: 16px;'>SEAT NO.</th>"
                + "                <th style='padding: 10px; text-align: center; border-bottom: 1px solid #dee2e6; background-color: #dc3545; color: #ffffff; font-size: 16px;'>VOUCHER NO.</th>"
                + "                <th style='padding: 10px; text-align: center; border-bottom: 1px solid #dee2e6; background-color: #dc3545; color: #ffffff; font-size: 16px;'>DEPARTING</th>"
                + "                <th style='padding: 10px; text-align: center; border-bottom: 1px solid #dee2e6; background-color: #dc3545; color: #ffffff; font-size: 16px;'>ARRIVING (ETA)</th>"
                + "            </tr>"
                + "            <tr>"
                + "                <td style='padding: 10px; text-align: center; border-bottom: 1px solid #dee2e6; background-color: #f8f9fa; color: #495057; font-size: 14px;'>1</td>"
                + "                <td style='padding: 10px; text-align: center; border-bottom: 1px solid #dee2e6; background-color: #f8f9fa; color: #495057; font-size: 14px;'>FM-DSYXY</td>"
                + "                <td style='padding: 10px; text-align: center; border-bottom: 1px solid #dee2e6; background-color: #f8f9fa; color: #495057; font-size: 14px;'>Yangon, 20 Aug 2024, 6:00 pm</td>"
                + "                <td style='padding: 10px; text-align: center; border-bottom: 1px solid #dee2e6; background-color: #f8f9fa; color: #495057; font-size: 14px;'>Mandalay and PyinOoLwin, 21 Aug 2024, 6:30 am</td>"
                + "            </tr>"
                + "        </table>"
                + "    </div>"
                + "    <div style='margin: 20px 0; color: #dc3545; font-weight: bold;'>"
                + "        <p>Remark: This ticket is for Myanmar National Card holder only. All Foreign and tourist passengers are suggested to buy Foreign/tourist ticket for security reason.</p>"
                + "    </div>"
                + "    <div style='text-align: center; font-size: 14px; color: #6c757d; margin-bottom: 20px;'>"
                + "        <p>Phone: +959788113366 Email: triphub@gmail.com</p>"
                + "        <p>Address: UTYCC, Mandallay-Pyin Oo Lwin Road, Pyin OO Lwin, Myanmar</p>"
                + "    </div>"
                + "    <div style='text-align: center; margin-top: 20px;'>"
                + "        <p style='font-size: 14px; color: #333;'>Please provide your feedback after your trip!</p>"
                + "        <img src='cid:" + imageCid + "' alt='QR Code for Feedback' style='width: 100px; height: 100px; border: 1px solid #ddd; border-radius: 5px;'>"
                + "    </div>"
                + "</div>"
                + "</body>"
                + "</html>";
            
                
      
      
        // Send the ticket via email
        try {
            FileSystemResource fileResource = new FileSystemResource(filePath);
            emailService.sendHtmlEmailWithInlineImage(passenger.getEmail(), "Your E-Ticket", ticketBody, fileResource, imageCid);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
            return "error";
        }

        return "success";
    }



        
 
    @GetMapping("/feedback/{passengerId}")
    public String giveFeedback(@PathVariable("passengerId") String passengerId, Model model) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        if (feedbackRepository.findByPassengerId(passengerId).isPresent()) {
            return "feedbackSuccess"; // Make sure to create a feedbackError.html template
        } else {
            model.addAttribute("passengerId", passengerId);
            model.addAttribute("fullname", passenger.getFullname());
            return "feedback";
        }
    }

    @PostMapping("/feedback")
    public String submitFeedback(@RequestParam String passengerId, @RequestParam String comments, Model model) {

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        Feedbacks feedback = new Feedbacks();
        feedback.setFeedbackId(UUID.randomUUID().toString()); // Generate a unique ID for the feedback
        feedback.setPassengerId(passengerId);
        feedback.setComments(comments);
        feedback.setFeedbackDate(Date.valueOf(LocalDate.now()));
        feedback.setPassenger(passenger);

        feedbackRepository.save(feedback);
        return "feedbackSuccess";
    }

}
