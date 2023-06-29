package main.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import main.entities.enums.BookingStatus;

@Getter
@Setter
public class ReservationPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Arrival Date è obbligatorio")
	private LocalDate arrivalDate;
	@NotNull(message = "ATTENZIONE!!! Il campo Departure Date è obbligatorio")
	private LocalDate departureDate;
	@NotNull(message = "ATTENZIONE!!! Il campo Booking Status è obbligatorio")
	private BookingStatus bookingStatus;
	@NotNull(message = "ATTENZIONE!!! Il campo Check In è obbligatorio")
	private LocalDateTime checkin;
	@NotNull(message = "ATTENZIONE!!! Il campo Check Out è obbligatorio")
	private LocalDateTime checkout;
}
