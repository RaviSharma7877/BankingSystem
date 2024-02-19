package com.RSstudies.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;
    
    @ManyToOne
    private Account account;
    
    private String actionType;
    private String actionDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
