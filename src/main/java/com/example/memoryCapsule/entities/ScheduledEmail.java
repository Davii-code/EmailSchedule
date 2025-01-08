package com.example.memoryCapsule.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
public class ScheduledEmail {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String recipientName;

    @Setter
    @Getter
    private String recipientEmail;

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private LocalDate scheduledDate;

    @Setter
    @Getter
    @ElementCollection
    private List<String> photoPaths; // Caminhos das fotos armazenadas.

    @Setter
    @Getter
    private boolean sent = false;

}