package com.psew.moneytransferapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.services.AccountService;

/**
 * Set of endpoints for storing and retrieving Accounts
 *
 * @author Biniam Asnake
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/list")
	public Iterable<Account> getAllAccounts() {

		return accountService.getAllAccounts();
	}

	@PostMapping("/create")
	public Account createAccount(@Valid @RequestBody Account account) {

		return accountService.createAccount(account);
	}

	/**
	 * LESSON: WRITING COMMENT FOR OPERATIONS THAT ARE SOMEHOW COMPLEX IS RECOMMENDED.
	 *
	 * Deletes an Account given a valid ID
	 *
	 * Steps:
	 * 1. Convert the string value in the path variable to Long
	 *  1.1. If conversion is successful
	 *      - Delete the account by calling the method in {@link AccountService}
	 *      - Check if the method call returned True and if so, return a 200 response
	 *  1.2. If conversion is not successful
	 *      -  respond with error message
	 *
	 * @param id Account ID passed as path variable with String datatype
	 *
	 * @return  ResponseEntity with HTTPStatus = 200 if account is deleted successfully
	 *          ResponseEntity with HTTPStatus = 404 if account deletion failed
	 */
	@DeleteMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<?> deleteByAccountId(@PathVariable("id") String id) {

		Long accountId;

		try {
			accountId = Long.parseLong(id);
		} catch(NumberFormatException nfe) {
			return ResponseEntity
					.badRequest()
					.body("{\"error\" : \"Account is not deleted properly. Please check the ID you passed in.\"}");
		}

		Boolean isAccountDeleted = accountService.deleteById(accountId);

		if (!isAccountDeleted) {
			return ResponseEntity
					.badRequest()
					.body("{\"error\" : \"Account is not deleted properly. Please try again.\"}");
		}

		return ResponseEntity
				.ok()
				.body("{\"message\" : \"Account with id " + id + " is successfully deleted\"}");
	}
}
