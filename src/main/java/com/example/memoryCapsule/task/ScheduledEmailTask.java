package com.example.memoryCapsule.task;

import com.example.memoryCapsule.entities.PhotosEmails;
import com.example.memoryCapsule.entities.ScheduledEmail;
import com.example.memoryCapsule.service.EmailSenderService;
import com.example.memoryCapsule.service.PhotosEmailSevice;
import com.example.memoryCapsule.service.ScheduledEmailService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    private PhotosEmailSevice photosEmailSevice;

    // @Scheduled(cron = "0 0 8 * * ?") // Executa diariamente às 8h
    @Scheduled(fixedRate = 60000) // Executa de minuto em minuto
    public void sendScheduledEmails() {
        LocalDate today = LocalDate.now();

        List<ScheduledEmail> emailsToSend = emailService.getEmailsToSend(today);

        List<Long> emailIds = emailsToSend.stream()
                .map(ScheduledEmail::getId)
                .collect(Collectors.toList());

        List<PhotosEmails> photosEmails = photosEmailSevice.getImagesByEmailIds(emailIds);

        for (ScheduledEmail email : emailsToSend) {
            try {
                List<File> attachments = convertPhotosToFiles(photosEmails, email.getId());

                senderService.sendEmail(
                        email.getRecipientEmail(),
                        "Memory Capsule " + email.getRecipientName(),
                        email.getMessage(),
                        attachments
                );

                emailService.markAsSent(email);

                cleanupTemporaryFiles(attachments);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<File> convertPhotosToFiles(List<PhotosEmails> photosEmails, Long emailId) throws IOException {
        List<File> files = new ArrayList<>();
        File tempDir = new File("src/main/resources/temp");

        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        for (PhotosEmails photo : photosEmails) {
            if (photo.getScheduledEmail().getId().equals(emailId)) {
                String fileName = photo.getName();

                String sanitizedFileName = fileName.replaceAll("(?<=\\.jpg)\\d+", "");

                File tempFile = File.createTempFile(sanitizedFileName, "." , tempDir);

                java.nio.file.Files.write(tempFile.toPath(), photo.getPhotoData());

                files.add(tempFile);
            }
        }
        return files;
    }

    private void cleanupTemporaryFiles(List<File> files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
