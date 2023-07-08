package main.runners;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import main.entities.Guest;
import main.entities.Reservation;
import main.entities.Room;
import main.entities.enums.BookingStatus;
import main.repositories.GuestRepository;
import main.repositories.ReservationRepository;
import main.repositories.RoomRepository;

@Order(6)
@Component
public class ReservationRunner implements CommandLineRunner {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	GuestRepository guestRepository;

	@Autowired
	RoomRepository roomRepository;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		Random random = new Random();
		LocalDate minDate = LocalDate.of(2022, 1, 1);
		LocalDate maxDate = LocalDate.now().plusMonths(3);
		List<Guest> guestsFromDB = guestRepository.findAll();
		List<Room> roomsFromDB = roomRepository.findAll();

		if (reservationRepository.count() == 0) {
			for (int i = 0; i < 25; i++) {
				try {
					BookingStatus bookingStatus = getRandomEnumValue(BookingStatus.class);
					Date randomArrivalDate = faker.date().between(convertToDate(minDate), convertToDate(maxDate));
					LocalDate arrivalDate = randomArrivalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int randomDays = random.nextInt(10) + 1;
					LocalDate departureDate = arrivalDate.plusDays(randomDays);
					LocalTime checkinTime = LocalTime.of(14, 0, 0);
					LocalDateTime checkin = arrivalDate.atTime(checkinTime);
					LocalTime checkoutTime = LocalTime.of(10, 0, 0);
					LocalDateTime checkout = departureDate.atTime(checkoutTime);
//					int randomGuestsIndex = random.nextInt(guestsFromDB.size());
//					Guest guest = guestsFromDB.get(randomGuestsIndex);
//					guestsFromDB.remove(randomGuestsIndex);

					int roomListSize = random.nextInt(3) + 1;
					List<Room> rooms = new ArrayList<>();
					for (int j = 0; j < roomListSize && !roomsFromDB.isEmpty(); j++) {
						int randomIndex = random.nextInt(roomsFromDB.size());
						Room randomRoom = roomsFromDB.get(randomIndex);
						rooms.add(randomRoom);
						roomsFromDB.remove(randomIndex);
					}
					Reservation reservation = new Reservation(arrivalDate, departureDate, bookingStatus, checkin,
							checkout, null, rooms, null);

					reservationRepository.save(reservation);

				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

			System.out
					.println("Nel DB della struttura sono presenti " + reservationRepository.count() + " prenotazioni");
		} else {
			System.out
					.println("Nel DB della struttura sono presenti " + reservationRepository.count() + " prenotazioni");
		}
	}

	public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();
		Random random = new Random();
		int index = random.nextInt(values.length);
		return values[index];
	}

	private static Date convertToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

}
