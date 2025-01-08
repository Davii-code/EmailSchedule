package com.example.memoryCapsule.controller;

import com.example.memoryCapsule.entities.PhotosEmails;
import com.example.memoryCapsule.entities.ScheduledEmail;
import com.example.memoryCapsule.service.PhotosEmailSevice;
import com.example.memoryCapsule.service.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/emails")
public class ScheduledEmailController {

    @Autowired
    private ScheduledEmailService service;

    @Autowired
    private PhotosEmailSevice servicePhotosEmail;

    @PostMapping("/schedule")
    public ResponseEntity<ScheduledEmail> scheduleEmail(@RequestBody ScheduledEmail email) {
        ScheduledEmail scheduled = service.scheduleEmail(email);
        return ResponseEntity.ok(scheduled);
    }

    @PostMapping("/uploadImg")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, Long scheduledEmailId) throws IOException {
        String uploadImage = servicePhotosEmail.uploadImage(file,scheduledEmailId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=servicePhotosEmail.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
}

