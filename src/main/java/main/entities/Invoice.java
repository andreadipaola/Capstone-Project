package main.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
	@JsonBackReference
	private Reservation reservation;

	@OneToOne(mappedBy = "invoice", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private Payment payment;

	public Invoice(double total, Reservation reservation, Payment payment) {
		this.total = total;
		this.reservation = reservation;
		this.payment = payment;
	}

}
