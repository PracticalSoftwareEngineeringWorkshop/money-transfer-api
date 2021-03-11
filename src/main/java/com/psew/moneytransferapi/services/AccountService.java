package com.psew.moneytransferapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.repositories.AccountRepository;

/**
 * Account Service accepts requests from the Controller and communicates with the repository to save and retrieve data.
 *
 * @author Biniam Asnake
 */
@Service
public class AccountService {

	@Autowired
	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Iterable<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	public Account createAccount(Account account) {
		//TODO validate
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
