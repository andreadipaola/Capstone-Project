package main.runners;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import main.entities.Guest;
import main.entities.enums.Gender;
import main.repositories.GuestRepository;

@Order(5)
@Component
public class GuestRunner implements CommandLineRunner {

	@Autowired
	GuestRepository guestRepository;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		LocalDate minDate = LocalDate.of(1923, 1, 1);
		LocalDate maxDate = LocalDate.of(2005, 12, 31);

		if (guestRepository.count() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					Gender gender = getRandomEnumValue(Gender.class);
					String firstName = faker.name().firstName();
					String lastName = faker.name().lastName();
					String language = "italiano";
					Date randomBirthdate = faker.date().between(convertToDate(minDate), convertToDate(maxDate));
					LocalDate dateOfBirth = randomBirthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

					String countryOfBirth = faker.address().country();
					String cityOfBirth = faker.address().city();
					String countryOfResidence = faker.address().country();
					String cityOfResidence = faker.address().city();
					String citizenship = faker.nation().nationality();
					String documentType = "passaporto";
					String documentNumber = faker.idNumber().valid();
					String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@google.com";
					String password = "1234";
					String phone = faker.phoneNumber().cellPhone();
					String creditCard = faker.finance().creditCard();
					String reasonOfTheTrip = faker.chuckNorris().fact();
					String note = null;
					String foodIntolerance = null;

					Guest guest = new Guest(gender, firstName, lastName, language, dateOfBirth, countryOfBirth,
							cityOfBirth, countryOfResidence, cityOfResidence, citizenship, documentType, documentNumber,
							email, password, phone, note, foodIntolerance, creditCard, reasonOfTheTrip, null);
					guestRepository.save(guest);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			System.out.println("Nella struttura alloggiano " + guestRepository.count() + " ospiti");

		} else {
			System.out.println("Nella struttura alloggiano " + guestRepository.count() + " ospiti");
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
