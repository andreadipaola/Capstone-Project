package main.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
	@Id
	@GeneratedValue
	@Column(name = "invoice_id")
	private UUID invoiceId;
	private double total;
	@OneToOne
	@JoinColumn(name = "reservarion_id")
	private Reservation reservation;
	@OneToOne(mappedBy = "invoice")
	private Payment payment;

	public Invoice(double total) {
		this.total = total;
	}

}
