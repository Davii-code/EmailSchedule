package com.example.memoryCapsule.controller;

import com.example.memoryCapsule.entities.ScheduledEmail;
import com.example.memoryCapsule.service.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class ScheduledEmailController {

    @Autowired
    private ScheduledEmailService service;

    @PostMapping("/schedule")
    public ResponseEntity<ScheduledEmail> scheduleEmail(@RequestBody ScheduledEmail email) {
        ScheduledEmail scheduled = service.scheduleEmail(email);
        return ResponseEntity.ok(scheduled);
    }
}

