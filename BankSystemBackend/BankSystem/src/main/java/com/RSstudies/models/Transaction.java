package com.RSstudies.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@ManyToOne
    private Account account;
	private double amount;
	private String transactionType;
	private LocalDate transactionDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
