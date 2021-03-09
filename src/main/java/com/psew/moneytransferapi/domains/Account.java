package com.psew.moneytransferapi.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	// Validation added as Email should be unique
	@Column(nullable = false, unique = true)
	private String email;

	// Validation added as Phone Number should be unique
	@Column(nullable = false, unique = true)
	private String phoneNumber;

	@Column(nullable = false)
	private Integer pin;

	@Column(nullable = false)
	private Double balance = 0.0;

	@Column(columnDefinition="boolean default true")
	private Boolean isVerified = Boolean.TRUE;
}