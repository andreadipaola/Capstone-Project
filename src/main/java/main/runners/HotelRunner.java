package main.runners;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import main.entities.Hotel;
import main.entities.Owner;
import main.repositories.HotelRepository;
import main.repositories.OwnerRepository;

@Order(2)
@Component
public class HotelRunner implements CommandLineRunner {

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	private OwnerRepository ownerRepository;
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
					String city = faker.address().city();
					String country = "Italia";
					Optional<Owner> optionaOwner = ownerRepository.findByEmail("andr3a.dipaola@gmail.com");
					Owner owner = optionaOwner.get();

					Hotel hotel = new Hotel(name, address, city, country, null, owner, null, null);
					hotelRepository.save(hotel);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			System.out.println("La catena comprende " + hotelRepository.count() + " strutture");
		} else {
			System.out.println("La catena comprende " + hotelRepository.count() + " strutture");
		}
	}

}
