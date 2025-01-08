package com.example.memoryCapsule.controller;

import com.example.memoryCapsule.entities.PhotosEmails;
import com.example.memoryCapsule.entities.ScheduledEmail;
import com.example.memoryCapsule.service.PhotosEmailSevice;
import com.example.memoryCapsule.service.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/emails")
public class ScheduledEmailController {

    @Autowired
    private ScheduledEmailService scheduledEmailService;

    @Autowired
    private PhotosEmailSevice photosEmailService;

    @PostMapping("/schedule")
    public ResponseEntity<ScheduledEmail> scheduleEmail(@RequestBody ScheduledEmail email) {
        // Verifique se há fotos para associar
        if (email.getPhotosEmails() != null && !email.getPhotosEmails().isEmpty()) {
            // Criar PhotosEmails para cada caminho de foto em base64
            List<PhotosEmails> photosEmails = new ArrayList<>();
            for (String base64Photo : email.getPhotosEmails()) {
                PhotosEmails photo = new PhotosEmails();
                byte[] photoPath = saveBase64Image(base64Photo);
                photo.setPhotoData(photoPath);
                photosEmailService.savePhotos(photo);
                photosEmails.add(photo);
            }

        }

        // Salvar o ScheduledEmail
        ScheduledEmail scheduled = scheduledEmailService.scheduleEmail(email);
        return ResponseEntity.ok(scheduled);
    }

    private byte[] saveBase64Image(String base64Photo) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Photo);
        return decodedBytes;
    }
}

