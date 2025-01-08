package com.example.memoryCapsule.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
@Table(name = "email_photos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotosEmails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id")
    @JsonIgnore
    private ScheduledEmail scheduledEmail;

    private String name;
    private String type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }
}
