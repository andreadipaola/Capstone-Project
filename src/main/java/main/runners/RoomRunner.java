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

import main.entities.Reservation;
import main.entities.Room;
import main.entities.RoomType;
import main.entities.enums.RoomStatus;
import main.repositories.ReservationRepository;
import main.repositories.RoomRepository;
import main.repositories.RoomTypeRepository;

@Order(6)
@Component
public class RoomRunner implements CommandLineRunner {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		Random random = new Random();
		LocalDate minDate = LocalDate.of(1999, 1, 1);
		LocalDate maxDate = LocalDate.now().minusMonths(1);
		List<RoomType> roomTypes = roomTypeRepository.findAll();
		List<Reservation> reservations = reservationRepository.findAll();

		if (roomRepository.count() == 0) {
			for (int i = 0; i < 25; i++) {
				try {
					int randomFloorNumber = random.nextInt(5) + 1;
					String floor = String.valueOf(randomFloorNumber);
					int randomRoomNumber = random.nextInt(25) + 1;
					String formattedRandomRoomNumber = randomRoomNumber > 9 ? String.valueOf(randomRoomNumber)
							: String.format("%02d", randomRoomNumber);
					String roomNumber = floor + formattedRandomRoomNumber;
					RoomStatus roomStatus = getRandomEnumValue(RoomStatus.class);
					boolean isSmoking = random.nextBoolean();

					int randomRoomTypesIndex = random.nextInt(roomTypes.size());
					RoomType roomType = roomTypes.get(randomRoomTypesIndex);

					Date randomDateAdded = faker.date().between(convertToDate(minDate), convertToDate(maxDate));
					LocalDate dateAdded = randomDateAdded.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

					int randomReservationIndex = random.nextInt(reservations.size());
					Reservation reservation = reservations.get(randomReservationIndex);

					Room room = new Room(roomNumber, floor, roomStatus, isSmoking, roomType, reservation);
					room.setDateAdded(dateAdded);

					roomRepository.save(room);

				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

			System.out.println("Nella struttura sono presenti " + roomRepository.count() + " camere");
		} else {
			System.out.println("Nella struttura sono presenti " + roomRepository.count() + " camere");
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
