package com.psew.moneytransferapi.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Account domain that represents a bank account
 *
 * @author Biniam Asnake
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
@Builder
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "First name is mandatory")
	@Size(max = 15, min = 3)
	private String firstName;

	@Column(nullable = false)
	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "Email is mandatory")
	private String email;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "Phone number is mandatory")
	private String phoneNumber;

	@NotNull(message = "Date of birth is mandatory.")
	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Column(nullable = false)
	@NotNull(message = "Pin is mandatory")
	private Integer pin;

	@Column(nullable = false)
	private Double balance = 0.0;

	@Column(columnDefinition="boolean default true")
	private Boolean isVerified = Boolean.TRUE;
}
