package com.example.memoryCapsule.service;

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
        return repository.save(email);
    }

    public List<ScheduledEmail> getEmailsToSend(LocalDate date) {
        return repository.findByScheduledDateAndSent(date, false);
    }

    public void markAsSent(ScheduledEmail email) {
        email.setSent(true);
        repository.save(email);
    }
}
