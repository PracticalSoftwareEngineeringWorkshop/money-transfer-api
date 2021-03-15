package com.psew.moneytransferapi.wrappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Data Transfer Object (DTO) for taking Transfer request as input from API
 *
 * @author Biniam Asnake
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransferRequest {

	private Long senderId;

	private Long receiverId;

	private Double amount;

	private String reason;
}
