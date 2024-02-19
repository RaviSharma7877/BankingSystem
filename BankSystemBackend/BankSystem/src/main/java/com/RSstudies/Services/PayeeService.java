package com.RSstudies.Services;

import java.util.List;

import com.RSstudies.Exceptions.InsufficientBalanceException;
import com.RSstudies.Exceptions.InvalidLoanAmountException;
import com.RSstudies.Exceptions.NoDataFoundException;
import com.RSstudies.models.Account;
import com.RSstudies.models.Payee;

public interface PayeeService {
	String addPayee(String accountNumber, double amount) throws InvalidLoanAmountException, NoDataFoundException, InsufficientBalanceException;
	Payee getPayee(Account account);
}
