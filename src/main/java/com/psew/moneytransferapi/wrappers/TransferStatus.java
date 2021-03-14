package com.psew.moneytransferapi.wrappers;

/**
 * Transfer status enumeration that represents the current state of the money transfer
 *
 * @author Biniam Asnake
 */
public enum TransferStatus {

	SUCCESSFUL,
	FAILED;

	/* It is possible to implement more statuses. To keep the application compact, we are not using the following statuses.
	PENDING,
	PROCESSING,
	CANCELED,
	 */
}
