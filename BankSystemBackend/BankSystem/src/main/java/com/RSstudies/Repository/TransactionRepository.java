package com.RSstudies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RSstudies.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
