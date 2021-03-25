package com.psew.moneytransferapi.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account domain that represents a bank account
 *
 * @author Biniam Asnake
 */
@Data                       // Lombok annotation to generate Setters and Getters, equals() and hash(), toString() methods
@AllArgsConstructor         // Generates All arguments constructor
@NoArgsConstructor          // Generates No arguments constructor
@Entity                     // Specifies that the class is an entity. It will generate a table in the database
@Table(name = "account")    // Specifies the table name. [Optional]
@Builder                    // Creates a builder for the class which makes constructing an object easy based on the Builder Pattern
public class Account {

	// Generates a unique ID for the Account automatically
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "First name is mandatory")    // the field must be not null and their trimmed length must be greater than zero.
	private String firstName;

	@Column(nullable = false)
	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	// Validation added as Email should be unique
	@Column(nullable = false, unique = true)
	@NotBlank(message = "Email is mandatory")
	private String email;

	// Validation added as Phone Number should be unique
	@Column(nullable = false, unique = true)
	@NotBlank(message = "Phone number is mandatory")
	private String phoneNumber;

	// We check for null only here because pin has Integer type
	// https://www.baeldung.com/java-bean-validation-not-null-empty-blank
	@Column(nullable = false)
	@NotNull(message = "Pin is mandatory")
	private Integer pin;

	@Column(nullable = false)
	// Use BigDecimal
	private Double balance = 0.0;

	@Column(columnDefinition="boolean default true")
	private Boolean isVerified = Boolean.TRUE;
}