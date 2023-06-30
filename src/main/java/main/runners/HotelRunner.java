package main.runners;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import main.entities.Hotel;
import main.repositories.HotelRepository;

@Order(1)
@Component
public class HotelRunner implements CommandLineRunner {

	@Autowired
	HotelRepository hotelRepository;
	// ATTENZIONE!!! SE SI VUOLE AUMENTARE IL NUMERO DI STRUTTURE BISOGNA AGGIUNGERE
	// ULTERIORI STRUTTURE ALL'ARRAY
	String[] hotelNames = { "The Grand Hotel", "Sunset Inn", "Mountain View Resort", "Ocean Breeze Hotel",
			"Royal Palace Suites", "Harmony Boutique Hotel", "Paradise Retreat", "Golden Sands Resort",
			"Majestic Plaza Hotel", "Serenity Lodge" };

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		if (hotelRepository.count() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String name = hotelNames[i];
					String address = faker.address().streetAddress();

					Hotel hotel = new Hotel(name, address);
					hotelRepository.save(hotel);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

		} else {
			System.out.println("La catena comprende " + hotelRepository.count() + " strutture");
		}
	}

}
