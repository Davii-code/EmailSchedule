package com.example.memoryCapsule.service;

import com.example.memoryCapsule.entities.PhotosEmails;
import com.example.memoryCapsule.entities.ScheduledEmail;
import com.example.memoryCapsule.repository.ScheduledEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduledEmailService {

    @Autowired
    private ScheduledEmailRepository repository;

    public ScheduledEmail scheduleEmail(ScheduledEmail email) {
        if (email.getPhotosEmails() != null) {
            for (PhotosEmails photo : email.getPhotosEmails()) {
                photo.setScheduledEmail(email);  // Associe o email às fotos
            }
        }

        ScheduledEmail savedEmail = repository.save(email);


        return savedEmail;
    }

    public List<ScheduledEmail> getEmailsToSend(LocalDate date) {
        return repository.findByScheduledDateAndSent(date, false);
    }

    public void markAsSent(ScheduledEmail email) {
        email.setSent(true);
        repository.save(email);
    }

    public ScheduledEmail getEmailById(long id) {
        return repository.findById(id).orElse(null);
    }
}
