package com.example.memoryCapsule.task;

import com.example.memoryCapsule.entities.ScheduledEmail;
import com.example.memoryCapsule.service.EmailSenderService;
import com.example.memoryCapsule.service.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledEmailTask {

    @Autowired
    private ScheduledEmailService emailService;

    @Autowired
    private EmailSenderService senderService;

//    @Scheduled(cron = "0 0 8 * * ?") // Executa diariamente às 8h
    @Scheduled(fixedRate = 60000) // Executa de minuto em minuto
    public void sendScheduledEmails() {
        LocalDate today = LocalDate.now();
        List<ScheduledEmail> emailsToSend = emailService.getEmailsToSend(today);

        for (ScheduledEmail email : emailsToSend) {
            try {
                senderService.sendEmail(
                        email.getRecipientEmail(),
                        "Memory Capsule",
                        email.getMessage(),
                        getFiles(email.getPhotoPaths())
                );
                emailService.markAsSent(email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<File> getFiles(List<String> paths) {
        if (paths == null) {
            return new ArrayList<>(); // Retorna uma lista vazia se paths for null
        }
        return paths.stream().map(File::new).collect(Collectors.toList());
    }

}

