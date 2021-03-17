package com.psew.moneytransferapi.controllers;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Set of endpoints for storing and retrieving Accounts
 *
 * @author Biniam Asnake
 */
@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public Iterable<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@Valid @RequestBody Account account) throws Exception {

        try {
            Account createdAccount = accountService.createAccount(account);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("{\"message\" : \"Account with id " + createdAccount.getId() + " is created successfully.\"}");

        } catch (Exception e) {
            log.error("Error creating Account in database: ", e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\" : \"Error creating Account in database. Please verify possible duplicates in the provided data.\"}");
        }
    }

    /**
     * Get account by Id
     * Given the id, get account data
     *
     * @param accountId account id to fetch
     * @return Account account object retrieved
     */
    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable("accountId") Long accountId) {

        return accountService.getAccountById(accountId);
    }

    /**
     * Updates account
     * Given the id and the updated account data, this operation makes modifications to an existing object
     *
     * @param accountId account id that needs to be deleted
     * @param account   account object to be updated
     * @return code = 200, message = Account with id {accountId} is updated successfully.
     * code = 400, error = Error updating Account in database. Please verify you send all required fields
     */
    @PutMapping("/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable("accountId") Long accountId, @RequestBody Account account) {
        try {
            accountService.updateAccount(accountId, account);
        } catch (Exception e) {
            log.error("Error updating Account in database: ", e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\" : \"Error updating Account in database. Please verify you send all required fields.\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("{\"message\" : \"Account with id " + accountId + " is updated successfully.\"}");
    }

    /**
     * LESSON: WRITING COMMENT FOR OPERATIONS THAT ARE SOMEHOW COMPLEX IS RECOMMENDED.
     * <p>
     * Deletes an Account given a valid ID
     * <p>
     * Steps:
     * - Delete the account by calling the method in {@link AccountService}
     * - Check if the method call returned True and if so, return a 200 response
     *
     * @param accountId Account ID passed as path variable with String datatype
     * @return ResponseEntity with HTTPStatus = 200 if account is deleted successfully
     * ResponseEntity with HTTPStatus = 404 if account deletion failed
     */
    @DeleteMapping(path = "/{accountId}", produces = "application/json")
    public ResponseEntity<?> deleteByAccountId(@PathVariable("accountId") Long accountId) {

        Boolean isAccountDeleted = accountService.deleteById(accountId);

        if (!isAccountDeleted) {
            return ResponseEntity
                    .badRequest()
                    .body("{\"error\" : \"Account is not deleted properly. Check the account Id and try again.\"}");
        }

        return ResponseEntity
                .ok()
                .body("{\"message\" : \"Account with id " + accountId + " is deleted successfully.\"}");
    }
}
