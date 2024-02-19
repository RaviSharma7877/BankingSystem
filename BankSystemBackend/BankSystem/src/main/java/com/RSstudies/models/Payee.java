package com.RSstudies.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Payee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payeeId;
    
    @JsonIgnore
    @ManyToOne
    private Account account;
    
    private double amount;
}