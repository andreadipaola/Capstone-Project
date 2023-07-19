package main.runners;

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
			User manager = new User("/assets/avatars/andrea.png", "Andrea", "Di Paola", "andr3a.dipaola@gmail.com",
					"123456789", Role.MANAGER);
			manager.setPassword(bcrypt.encode(manager.getPassword()));
			userRepository.save(manager);
			for (int i = 0; i < 5; i++) {
				try {

					String firstName = faker.name().firstName();
					String lastName = faker.name().lastName();
					String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@google.com";
					String password = "1234";

					Role role = Role.GUEST;

					User user = new User(null, firstName, lastName, email, password, role);
					user.setPassword(bcrypt.encode(user.getPassword()));
					userRepository.save(user);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
//			User admin = new User("/assets/avatars/andrea.png", "Andrea", "Di Paola", "andr3a.dipaola@gmail.com",
//					"123456789");
//			userRepository.save(admin);
			System.out.println("Il team della struttura comprende " + userRepository.count() + " user");

		} else {
			System.out.println("Il team della struttura comprende " + userRepository.count() + " user");
		}
	}

}
