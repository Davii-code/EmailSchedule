package com.example.memoryCapsule.service;

import com.example.memoryCapsule.entities.PhotosEmails;
import com.example.memoryCapsule.entities.ScheduledEmail;
import com.example.memoryCapsule.repository.PhotosEmailRepository;
import com.example.memoryCapsule.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class PhotosEmailSevice {

    @Autowired
    private ScheduledEmailService emailService;

    @Autowired
    private PhotosEmailRepository repository;

    public String uploadImage(MultipartFile file, Long scheduledEmailId) throws IOException {
        PhotosEmails imageData = new PhotosEmails();
        ScheduledEmail scheduledEmail = emailService.getEmailById(scheduledEmailId);
        imageData.setName(file.getOriginalFilename());
        imageData.setType(file.getContentType());
        imageData.setPhotoData(ImageUtils.compressImage(file.getBytes()));
        imageData.setScheduledEmail(scheduledEmail);

        imageData = repository.save(imageData);

        if (imageData != null) {
            return "File uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<PhotosEmails> dbImageData = repository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getPhotoData());
        return images;
    }

    @Transactional
    public List<PhotosEmails> getImagesByEmailIds(List<Long> emailIds) {
        return repository.findByScheduledEmail_IdIn(emailIds);
    }

    public PhotosEmails getImagesByEmailId(Long emailId) {
        return repository.findByScheduledEmail_Id(emailId);
    }


}
