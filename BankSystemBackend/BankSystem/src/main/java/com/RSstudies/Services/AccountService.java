package com.RSstudies.Services;

import java.util.List;

import com.RSstudies.Exceptions.NoDataFoundException;
import com.RSstudies.models.Account;

public interface AccountService {
	Account createAccount(Account account, int userId) throws NoDataFoundException;
	String updateAccountStatus(int accountId, String status);
	String deleteAccount(int accountId);
	Account getAccountById(int accountId);
	List<Account> getAllAccount();
}
