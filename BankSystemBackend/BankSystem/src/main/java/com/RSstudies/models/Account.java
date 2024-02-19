package com.RSstudies.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    
    
    @OneToOne
    private Loan loans;
    
    @OneToOne
    private Payee payees;
    
    
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    private String accountNumber;
    
    private double balance;
    private String type;
    private String status;
    private LocalDateTime createdAt;
}
