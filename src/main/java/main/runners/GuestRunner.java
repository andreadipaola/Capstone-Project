package main.runners;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import main.entities.Guest;
import main.repositories.GuestRepository;

@Order(2)
@Component
public class GuestRunner implements CommandLineRunner {

	@Autowired
	GuestRepository guestRepository;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		if (guestRepository.count() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String firstName = faker.name().firstName();
					String lastName = faker.name().lastName();
					String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@google.com";
					String password = "1234";
					String phone = faker.phoneNumber().cellPhone();
					String creditCard = faker.finance().creditCard();

					Guest guest = new Guest(firstName, lastName, email, password, phone, creditCard);
					guestRepository.save(guest);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

		} else {
			System.out.println("In questa struttura ci sono " + guestRepository.count() + " ospiti");
		}
	}

}
