package com.RSstudies.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RSstudies.Repository.AccountRepository;
import com.RSstudies.Repository.TransactionRepository;
import com.RSstudies.models.Account;
import com.RSstudies.models.Transaction;
@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;

	@Override
	public String deposit(Account account, double amount) {
		Account acc = accountRepo.findById(account.getAccountId()).orElseThrow();
		double balance = acc.getBalance()+amount;
		acc.setBalance(balance);
return "Amount deposit successfully";
	}

	@Override
	public String withdraw(Account account, double amount) {
		Account acc = accountRepo.findById(account.getAccountId()).orElseThrow();
		double balance = acc.getBalance();
		if(balance > amount) {
			balance -= amount;
			acc.setBalance(balance);
			return "Amount deposit successfully";
		}
return "You don't have sufficient balance";
	}

	@Override
	public String transfer(String fromAccountNumber, String toAccountNumber, double amount) {
		Account fromAcc = accountRepo.findByAccountNumber(fromAccountNumber).orElseThrow();
		Account toAcc = accountRepo.findByAccountNumber(toAccountNumber).orElseThrow();
		double fromAccBalance = fromAcc.getBalance();
		if(fromAccBalance > amount) {
			fromAccBalance -= amount;
			double toAccBalance = fromAcc.getBalance()+amount;
			toAcc.setBalance(toAccBalance);
			return "Amount deposit successfully";
		}
		return "You don't have sufficient balance";
	}
	
	@Override
	public List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return transactionRepo.findAll();
	}

}
