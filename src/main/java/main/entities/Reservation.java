package main.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.enums.BookingStatus;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
	@Id
	@GeneratedValue
	@Column(name = "reservation_id")
	private UUID reservationId;
	@Column(name = "arrival_date")
	private LocalDate arrivalDate;
	@Column(name = "departure_date")
	private LocalDate departureDate;
	@Column(name = "booking_status")
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;

	@ManyToOne
	@JoinColumn(name = "guest_id")
	@JsonBackReference
	private Guest guest;

	@OneToMany(mappedBy = "reservation")
	@JsonManagedReference
	private List<Room> rooms = new ArrayList<>();

	@OneToOne(mappedBy = "reservation")
	@JsonManagedReference
	private Invoice invoice;

	public Reservation(LocalDate arrivalDate, LocalDate departureDate, BookingStatus bookingStatus, Guest guest,
			List<Room> rooms, Invoice invoice) {
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.bookingStatus = bookingStatus;
		this.guest = guest;
		this.rooms = rooms;
		this.invoice = invoice;
	}

}
