package com.example.memoryCapsule.repository;

import com.example.memoryCapsule.entities.PhotosEmails;
import com.example.memoryCapsule.entities.ScheduledEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PhotosEmailRepository extends JpaRepository<PhotosEmails, Long> {
}
