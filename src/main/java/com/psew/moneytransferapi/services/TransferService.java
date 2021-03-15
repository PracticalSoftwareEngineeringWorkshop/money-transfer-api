package com.psew.moneytransferapi.services;

import static com.psew.moneytransferapi.exceptions.TransferException.INSUFFICIENT_BALANCE_EXCEPTION;
import static com.psew.moneytransferapi.exceptions.TransferException.INVALID_TRANSFER_ID_EXCEPTION;
import static com.psew.moneytransferapi.exceptions.TransferException.SAME_ACCOUNT_TRANSFER_EXCEPTION;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.domains.Transfer;
import com.psew.moneytransferapi.exceptions.AccountException;
import com.psew.moneytransferapi.exceptions.TransferException;
import com.psew.moneytransferapi.repositories.TransferRepository;
import com.psew.moneytransferapi.wrappers.TransferRequest;
import com.psew.moneytransferapi.wrappers.TransferStatus;

/**
 * Transfer Service that defines the main business logic of creating, reading, updating and deleting transfers.
 *
 * @author Biniam Asnake
 */
@Service
public class TransferService {

	private final AccountService accountService;

	private final TransferRepository transferRepository;

	@Autowired
	public TransferService(AccountService accountService, TransferRepository transferRepository) {
		this.accountService = accountService;
		this.transferRepository = transferRepository;
	}

	/**
	 * Retrieves transfer based on id
	 *
	 * @param transferId: Long variable representing the ID of the transfer
	 * @return Transfer object if found. Otherwise, null
	 */
	public Transfer getTransferById(Long transferId) {

		return transferRepository.findById(transferId)
				.orElseThrow(() -> new TransferException(INVALID_TRANSFER_ID_EXCEPTION));
	}

	/**
	 * Main business logic for transferring money from one account to another.
	 * <p>
	 * Requirements for successful money transfer:
	 * 1. Sender AND receiver accounts must not be null
	 * 2. Both sender and receiver cannot be the same account
	 * 3. The Senders account balance should be equal or greater than the transfer amount.
	 * <p>
	 * Otherwise, money transfer is not possible.
	 *
	 * @param transferRequest: transfer object containing information about the money transfer
	 * @return The created Transfer object if everything is successful.
	 * Otherwise, either AccountException or TransferException will be thrown.
	 */
	public Transfer transferMoney(TransferRequest transferRequest) throws AccountException, TransferException {

		Account sender = accountService.getAccountById(transferRequest.getSenderId());

		Account receiver = accountService.getAccountById(transferRequest.getReceiverId());

		// This operation can be replaced by overriding equals() and hashcode() in Account domain.
		// We are doing this here not to add so much code on Account domain. Similar implementation is provided in Transfer domain class.
		if (sender.getId().equals(receiver.getId())) {
			throw new TransferException(SAME_ACCOUNT_TRANSFER_EXCEPTION);
		}

		if (sender.getBalance() < transferRequest.getAmount()) {
			throw new TransferException(INSUFFICIENT_BALANCE_EXCEPTION);
		} else {
			Double newSenderBalance = sender.getBalance() - transferRequest.getAmount();
			Account updatedSenderAccount = accountService.updateAccountBalance(sender, newSenderBalance);

			Double newReceiverBalance = receiver.getBalance() + transferRequest.getAmount();
			Account updatedReceiverAccount = accountService.updateAccountBalance(receiver, newReceiverBalance);

			Transfer transfer = Transfer.builder()
					.sender(updatedSenderAccount)
					.receiver(updatedReceiverAccount)
					.amount(transferRequest.getAmount())
					.reason(transferRequest.getReason())
					.date(new Date())
					.status(TransferStatus.SUCCESSFUL)
					.build();

			return transferRepository.save(transfer);
		}
	}

	/**
	 * Deletes transfer from the database
	 *
	 * @param transferId: Id of the transfer to be deleted
	 * @return True if deletion is successful. Else, False will be returned
	 */
	public Boolean deleteById(Long transferId) {
		try {
			transferRepository.deleteById(transferId);
			return Boolean.TRUE;
		} catch(Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
}
