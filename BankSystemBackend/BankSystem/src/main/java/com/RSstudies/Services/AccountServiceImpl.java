package com.RSstudies.Services;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RSstudies.Exceptions.NoDataFoundException;
import com.RSstudies.Repository.AccountRepository;
import com.RSstudies.Repository.UserRepository;
import com.RSstudies.models.Account;
import com.RSstudies.models.Users;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	private String generateRandomAccountNumber() {
        // Define the characters for the alphanumeric string
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Define the length of the account number
        int length = 16;

        // Create a StringBuilder to store the generated account number
        StringBuilder sb = new StringBuilder(length);

        // Create a secure random object
        SecureRandom random = new SecureRandom();

        // Generate a random alphanumeric string of length 16
        for (int i = 0; i < length; i++) {
            // Get a random index from the chars string
            int index = random.nextInt(chars.length());

            // Append the character at the random index to the StringBuilder
            sb.append(chars.charAt(index));
        }

        // Return the generated account number as a string
        return sb.toString();
    }

	@Override
	public Account createAccount(Account account, int userId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		Users user = userRepo.findById(userId).orElseThrow();
		String accountNumber = generateRandomAccountNumber();

        // Set the generated accountNumber to the account
        account.setAccountNumber(accountNumber);
        account.setStatus("Active");
		user.setAccount(account);
		return accountRepo.save(account);
	}
	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}

	@Override
	public String updateAccountStatus(int accountId, String status) {
		Account acc = accountRepo.findById(accountId).orElseThrow();
		acc.setStatus(status);
		return "Account status updated successfully";
	}

	@Override
	public String deleteAccount(int accountId) {
		Account acc = accountRepo.findById(accountId).orElseThrow();
		accountRepo.delete(acc);
		return "Account deleted successfully";
	}

	@Override
	public Account getAccountById(int accountId) {
		Account acc = accountRepo.findById(accountId).orElseThrow();
		return acc;
	}

}