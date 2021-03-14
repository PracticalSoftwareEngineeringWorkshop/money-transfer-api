package com.psew.moneytransferapi.controllers;

import com.psew.moneytransferapi.domains.Transfer;
import com.psew.moneytransferapi.services.TransferService;
import com.psew.moneytransferapi.wrappers.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API endpoints for performing CRUD operation on Money Transfer
 *
 * @author Biniam Asnake
 */
@RestController
@RequestMapping("/api/transfer")
public class TransferController {

	private final TransferService transferService;

	@Autowired
	public TransferController(TransferService transferService) {
		this.transferService = transferService;
	}

	/**
	 * Create a money transfer operation. The API call to /api/transfer will be redirected here.
	 * <p>
	 * Requirements for successful money transfer:
	 * 1. Sender AND receiver accounts must not be null
	 * 2. Both sender and receiver cannot be the same account
	 * 3. The Senders account balance should be equal or greater than the transfer amount.
	 * Otherwise, money transfer is not possible.
	 *
	 * @param transferRequest: Created transfer DTO (data transfer object)
	 * @return If successful, the created Transfer object with all details will be returned.
	 */
	@PostMapping()
	public Transfer transferMoney(@RequestBody TransferRequest transferRequest) {

		return transferService.transferMoney(transferRequest);
	}

	/**
	 * Retrieves transfer based on id
	 *
	 * @param transferId transfer id to retrieve
	 * @return Transfer
	 * if validation failed, "Invalid transfer ID supplied" will be returned
	 */
	@GetMapping(path = "/{transferId}")
	public Transfer getTransferById(@PathVariable("transferId") Long transferId) {

		return transferService.getTransferById(transferId);
	}

	/**
	 * Given the id of the transfer data to delete, it removes it from the database
	 *
	 * @param transferId transfer id that needs to be deleted
	 * @return Transfer
	 * code = 400, message = "Invalid transfer supplied"
	 * code = 404, message = "transfer not found"
	 */
	@DeleteMapping(path = "/{transferId}", produces = "application/json")
	public ResponseEntity<?> deleteTransfer(@PathVariable("transferId") Long transferId) {

		Boolean isTransferDeleted = transferService.deleteById(transferId);

		if(!isTransferDeleted) {
			return ResponseEntity
					.badRequest()
					.body("{\"error\" : \"Transfer is not deleted properly. Check the transfer Id and try again.\"}");
		}

		return ResponseEntity
				.ok()
				.body("{\"message\" : \"Transfer with id " + transferId + " is successfully deleted\"}");
	}
}
