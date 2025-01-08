package com.example.memoryCapsule.service;

import com.example.memoryCapsule.entities.PhotosEmails;
import com.example.memoryCapsule.repository.PhotosEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PhotosEmailSevice {


    @Autowired
    private PhotosEmailRepository repository;

    public PhotosEmails savePhotos(PhotosEmails photoPaths) {
        return repository.save(photoPaths);
    }



}
