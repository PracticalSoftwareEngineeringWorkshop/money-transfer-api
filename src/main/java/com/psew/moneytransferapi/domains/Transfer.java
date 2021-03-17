package com.psew.moneytransferapi.domains;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.psew.moneytransferapi.wrappers.TransferStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Money Transfer domain that represents sending of money from one valid account to another one
 *
 * @author Biniam Asnake
 */
@Data                       // Lombok annotation to generate Setters and Getters, toString() methods
@AllArgsConstructor         // Generates All arguments constructor
@NoArgsConstructor          // Generates No arguments constructor
@Entity                     // Specifies that the class is an entity. It will generate a table in the database
@Table(name = "transfer")   // Specifies the table name. [Optional]
@Builder                    // Creates a builder for the class which makes constructing an object easy based on the Builder Pattern
public class Transfer {

	// Generates a unique ID for the Transfer automatically
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private String uuid;

	@NotNull(message = "Sender account is mandatory")    // the field must be not null
	@ManyToOne(fetch = FetchType.EAGER)
	private Account sender;

	@NotNull(message = "Receiver account is mandatory")    // the field must be not null
	@ManyToOne(fetch = FetchType.EAGER)
    private Account receiver;

	@NotNull(message = "Amount is mandatory")    // the field must be not null
	@Column(nullable = false)
    private Double amount;

	@Column(nullable = false)
    private Date date;

	@Column(columnDefinition="text")
	// Setting columnDefinition to text makes sure that we can enter as much text as we want.
	// default: nullable = true
	private String reason;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private TransferStatus status;

	@PrePersist
	void assignUuidBeforePersist() {

		if (uuid == null)
			uuid = UUID.randomUUID().toString();
	}

    /**
     * Overridden equals and hashCode methods to compare two Transfer objects based on sender, receiver, amount, reason and date.
     *
     * @param object: Transfers to compare
     * @return true if objects are equal. Otherwise, false.
     */
    @Override
    public boolean equals(Object object) {

        if (object == this) return true;
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer transfer = (Transfer) object;
        return Objects.equals(sender, transfer.sender) &&
                Objects.equals(receiver, transfer.receiver) &&
                Objects.equals(amount, transfer.amount) &&
				Objects.equals(reason, transfer.reason) &&
                Objects.equals(date, transfer.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, amount, reason, date);
    }
}
