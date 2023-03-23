package com.psew.moneytransferapi.services;

import com.psew.moneytransferapi.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.exceptions.AccountException;
import com.psew.moneytransferapi.repositories.AccountRepository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.psew.moneytransferapi.exceptions.AccountException.*;

/**
 * Account Service accepts requests from the Controller and communicates with the repository to save and retrieve data.
 *
 * @author Biniam Asnake
 */
@Service
public class AccountService {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Iterable<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	public Account createAccount(Account account) {

		int age = AgeCalculator.calculateAge(account.getDateOfBirth(), LocalDate.now());

		if (age > 15) {
			return accountRepository.save(account);
		} else {
			throw new AccountException(UNDER_AGE_ACCOUNT_CREATION_EXCEPTION);
		}
	}

	public Account getAccountById(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new AccountException(ACCOUNT_NOT_FOUND_EXCEPTION));
	}

	@Transactional
	public void updateAccount(Long accountId, Account account) throws SQLException {

		accountRepository.updateAccount(accountId, account.getFirstName(), account.getLastName(), account.getEmail(), account.getPhoneNumber(), account.getPin());
	}

	public Account updateAccountBalance(Account account, Double newBalance) {

		if (account == null) {
			throw new AccountException(INVALID_ACCOUNT_EXCEPTION);
		}

		account.setBalance(newBalance);

		return accountRepository.save(account);
	}

	public Boolean deleteById(Long accountId) {
		try {
			accountRepository.deleteById(accountId);
			return Boolean.TRUE;
		} catch(Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
}
