package main.runners;

import java.time.LocalDate;
import java.time.ZoneId;
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
import main.entities.enums.BookingStatus;
import main.repositories.GuestRepository;
import main.repositories.ReservationRepository;
import main.repositories.RoomRepository;

@Order(5)
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

		if (reservationRepository.count() == 0) {
			for (int i = 0; i < 25; i++) {
				try {
					BookingStatus bookingStatus = getRandomEnumValue(BookingStatus.class);
					Date randomArrivalDate = faker.date().between(convertToDate(minDate), convertToDate(maxDate));
					LocalDate arrivalDate = randomArrivalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int randomDays = random.nextInt(10) + 1;
					LocalDate departureDate = arrivalDate.plusDays(randomDays);

					int randomGuestsIndex = random.nextInt(guestsFromDB.size());
					Guest guest = guestsFromDB.get(randomGuestsIndex);

					// SE DECOMMENTIAMO LA RIGA SOTTOSTANTE E GLI UTENTI GENERATI FOSSERO MENO DELLE
					// PRENOTAZIONI SI ANDREBBE IN CONTRO ALL'ERRORE OUTOFBOUND QUESTO PERCHE LA
					// LISTA DEI GUESTS SI VUOTEREBBE E IN QUEL MOMENTO NON AVREMMO PIU GUESTS DA
					// ASSEGNARE ALLE PRENOTAZIONI
					// guestsFromDB.remove(randomGuestsIndex);

					Reservation reservation = new Reservation(arrivalDate, departureDate, bookingStatus, guest, null,
							null);

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
