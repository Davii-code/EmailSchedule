package com.example.memoryCapsule.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "email_photos")
public class PhotosEmails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id")
    private ScheduledEmail scheduledEmail;

    @Lob
    @Column(name = "photo_data")
    private byte[] photoData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScheduledEmail getScheduledEmail() {
        return scheduledEmail;
    }

    public void setScheduledEmail(ScheduledEmail scheduledEmail) {
        this.scheduledEmail = scheduledEmail;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }
}
