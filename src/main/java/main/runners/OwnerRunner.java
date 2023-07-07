package main.runners;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import main.entities.Owner;
import main.entities.enums.Gender;
import main.repositories.OwnerRepository;

@Order(2)
@Component
public class OwnerRunner implements CommandLineRunner {

	@Autowired
	OwnerRepository ownerRepository;

	@Override
	public void run(String... args) throws Exception {

		if (ownerRepository.count() == 0) {

			Owner owner = new Owner(Gender.MAN, "Andrea", "Di Paola", "IT", LocalDate.of(1986, 02, 15), "Italia",
					"Roma", "Italia", "Roma", "italiana", "Passaporto", "AA999ZZ", "andr3a.dipaola@gmail.com", "1234",
					"333321654");
			ownerRepository.save(owner);
			System.out.println("La catena è posseduta da " + ownerRepository.count() + " proprietario");
		} else {
			System.out.println("La catena è posseduta da " + ownerRepository.count() + " proprietario");
		}
	}

}
