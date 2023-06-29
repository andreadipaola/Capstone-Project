package main.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	private LocalDateTime checkin;
	private LocalDateTime checkout;
	@ManyToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;
	@OneToMany(mappedBy = "reservation")
	private List<Room> rooms = new ArrayList<>();
	@OneToOne(mappedBy = "reservation")
	private Review review;
	@OneToOne(mappedBy = "reservation")
	private Invoice invoice;

}
