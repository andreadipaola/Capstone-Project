package main.runners;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import main.entities.User;
import main.entities.enums.Role;
import main.repositories.UserRepository;

@Order(1)
@Component
public class UserRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcrypt;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		if (userRepository.count() == 0) {
			User andrea = new User("/assets/avatars/andrea2.png", "Andrea", "Di Paola", "andr3a.dipaola@gmail.com",
					"123456789", "3356193161", LocalDate.of(1986, 2, 15), Role.MANAGER);
			andrea.setPassword(bcrypt.encode(andrea.getPassword()));
			userRepository.save(andrea);

			User cracco = new User(null, "Carlo", "Cracco", "carlo.cracco@yahoo.com", "123456789", "333123456",
					LocalDate.of(1965, 10, 8), Role.RECEPTIONIST);
			cracco.setPassword(bcrypt.encode(cracco.getPassword()));
			userRepository.save(cracco);

			User cannavacciolo = new User(null, "Antonino", "Cannavacciulo", "antonino.cannavacciulo@hotmail.com",
					"123456789", "333456789", LocalDate.of(1975, 4, 16), Role.MANAGER);
			cannavacciolo.setPassword(bcrypt.encode(cannavacciolo.getPassword()));
			userRepository.save(cannavacciolo);

			User locatelli = new User(null, "Giorgio", "Locatelli", "giorgio.locatelli@gmail.com", "123456789",
					"3356193161", LocalDate.of(1963, 4, 7), Role.GUEST);
			locatelli.setPassword(bcrypt.encode(locatelli.getPassword()));
			userRepository.save(locatelli);
//			for (int i = 0; i < 5; i++) {
//				try {
//
//					String firstName = faker.name().firstName();
//					String lastName = faker.name().lastName();
//					String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@google.com";
//					String password = "123456789";
//					String phone = faker.phoneNumber().cellPhone();
//					LocalDate dateAdded = LocalDate.now();
//					Role role = Role.GUEST;
//
//					User user = new User(null, firstName, lastName, email, password, phone, dateAdded, role);
//					user.setPassword(bcrypt.encode(user.getPassword()));
//					userRepository.save(user);
//				} catch (Exception ex) {
//					System.out.println(ex);
//				}
//			}

			System.out.println("Il team della struttura comprende " + userRepository.count() + " user");

		} else {
			System.out.println("Il team della struttura comprende " + userRepository.count() + " user");
		}
	}

}
