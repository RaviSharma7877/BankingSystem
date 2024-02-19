package com.RSstudies.Services;

import java.util.List;

import com.RSstudies.models.Account;
import com.RSstudies.models.Transaction;

public interface TransactionService {
	String deposit(Account account, double amount);
	String withdraw(Account account, double amount);
	String transfer(String fromAccountNumber, String toAccountNumber, double amount);
	List<Transaction> getAllTransaction();
}
