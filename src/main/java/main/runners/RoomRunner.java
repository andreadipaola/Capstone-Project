//package main.runners;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.Locale;
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Faker;
//
//import main.entities.Receptionist;
//import main.entities.enums.Gender;
//import main.repositories.ReceptionistRepository;
//
//@Order(4)
//@Component
//public class RoomRunner implements CommandLineRunner {
//
//	@Autowired
//	ReceptionistRepository receptionistRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
//		LocalDate minDate = LocalDate.of(1923, 1, 1);
//		LocalDate maxDate = LocalDate.of(2005, 12, 31);
//
//		if (receptionistRepository.count() == 0) {
//			for (int i = 0; i < 25; i++) {
//				try {
//					Gender gender = getRandomEnumValue(Gender.class);
//					String firstName = faker.name().firstName();
//					String lastName = faker.name().lastName();
//					String language = "italiano";
//					Date randomBirthdate = faker.date().between(convertToDate(minDate), convertToDate(maxDate));
//					LocalDate dateOfBirth = randomBirthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//					String countryOfBirth = faker.address().country();
//					String cityOfBirth = faker.address().city();
//					String countryOfResidence = faker.address().country();
//					String cityOfResidence = faker.address().city();
//					String citizenship = faker.nation().nationality();
//					String documentType = "passaporto";
//					String documentNumber = faker.idNumber().valid();
//					String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@google.com";
//					String password = "1234";
//					String phone = faker.phoneNumber().cellPhone();
//
//					Receptionist receptionist = new Receptionist(gender, firstName, lastName, language, dateOfBirth,
//							countryOfBirth, cityOfBirth, countryOfResidence, cityOfResidence, citizenship, documentType,
//							documentNumber, email, password, phone);
//					receptionistRepository.save(receptionist);
//				} catch (Exception ex) {
//					System.out.println(ex);
//				}
//			}
//
//		} else {
//			System.out.println("Nella catena lavorano " + receptionistRepository.count() + " receptionist");
//		}
//	}
//
//	public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
//		T[] values = enumClass.getEnumConstants();
//		Random random = new Random();
//		int index = random.nextInt(values.length);
//		return values[index];
//	}
//
//	private static Date convertToDate(LocalDate localDate) {
//		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//	}
//
//}
