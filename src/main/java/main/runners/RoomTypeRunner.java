package main.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import main.entities.RoomType;
import main.repositories.RoomTypeRepository;

@Order(4)
@Component
public class RoomTypeRunner implements CommandLineRunner {

	@Autowired
	RoomTypeRepository roomTypeRepository;

	@Override
	public void run(String... args) throws Exception {

		if (roomTypeRepository.count() == 0) {
			RoomType singleRoom = new RoomType("Single Room", "SR", "Camera Singola", 80, 2, null);
			roomTypeRepository.save(singleRoom);

			RoomType doubleRoom = new RoomType("Double Room", "DR", "Camera Doppia", 140, 3, null);
			roomTypeRepository.save(doubleRoom);

			RoomType suite = new RoomType("Suite", "SU", "Suite", 180, 3, null);
			roomTypeRepository.save(suite);

			RoomType deluxe = new RoomType("Deluxe", "DE", "Camera Deluxe", 210, 4, null);
			roomTypeRepository.save(deluxe);

			System.out.println("Nella struttura ci sono " + roomTypeRepository.count() + " tipi di camere");
		} else {
			System.out.println("Nella struttura ci sono " + roomTypeRepository.count() + " tipi di camere");
		}
	}

}
