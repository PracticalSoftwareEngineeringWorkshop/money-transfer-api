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

	@PostMapping()
	public Transfer transferMoney(@RequestBody TransferRequest transferRequest) {

		return transferService.transferMoney(transferRequest);
	}

	@GetMapping(path = "/{transferId}")
	public Transfer getTransferById(@PathVariable("transferId") Long transferId) {

		return transferService.getTransferById(transferId);
	}

	@GetMapping("/list")
	public Iterable<Transfer> getAllTransfers() {
		return transferService.getAllTransfers();
	}

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
