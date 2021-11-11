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

import javax.transaction.Transactional;

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

	public Transfer getTransferById(Long transferId) {

		return transferRepository.findById(transferId)
				.orElseThrow(() -> new TransferException(INVALID_TRANSFER_ID_EXCEPTION));
	}

	public Iterable<Transfer> getAllTransfers() {
		return transferRepository.findAll();
	}

	public Transfer transferMoney(TransferRequest transferRequest) throws AccountException, TransferException {

		Account sender = accountService.getAccountById(transferRequest.getSenderId());

		Account receiver = accountService.getAccountById(transferRequest.getReceiverId());

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
