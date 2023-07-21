package main.responses;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.Guest;
import main.entities.Room;
import main.entities.enums.BookingStatus;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {
	private UUID reservationId;
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	private BookingStatus bookingStatus;
	private Guest guest;
	private List<Room> rooms;
//	private Invoice invoice;
}
