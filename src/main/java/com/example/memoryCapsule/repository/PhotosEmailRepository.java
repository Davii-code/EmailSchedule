package com.example.memoryCapsule.repository;

import com.example.memoryCapsule.entities.PhotosEmails;
import com.example.memoryCapsule.entities.ScheduledEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhotosEmailRepository extends JpaRepository<PhotosEmails, Long> {

    Optional<PhotosEmails> findByName(String fileName);
    List<PhotosEmails> findByScheduledEmail_IdIn(List<Long> emailIds);
    PhotosEmails findByScheduledEmail_Id(Long emailId);

}
