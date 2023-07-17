package main.runners;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import main.entities.User;
import main.repositories.UserRepository;

@Order(1)
@Component
public class UserRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		if (userRepository.count() == 0) {
			for (int i = 0; i < 5; i++) {
				try {

					String firstName = faker.name().firstName();
					String lastName = faker.name().lastName();
					String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@google.com";
					String password = "1234";

					User user = new User(null, firstName, lastName, email, password);
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
