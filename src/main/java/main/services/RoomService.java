package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Room;
import main.exceptions.NotFoundException;
import main.payloads.RoomPayload;
import main.repositories.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public Page<Room> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return roomRepository.findAll(pageable);
	}

	public Room create(RoomPayload body) {
		Room room = new Room(body.getRoomNumber(), body.getFloor(), body.getRoomStatus(), null, null);
		return roomRepository.save(room);
	}

	public Room findById(UUID id) throws NotFoundException {
		return roomRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! La camera cercata non è stata trovata!"));
	}

	public Room findByIdAndUpdate(UUID id, RoomPayload body) throws NotFoundException {
		Room found = this.findById(id);

		found.setRoomId(id);
		found.setRoomNumber(body.getRoomNumber());
		found.setFloor(body.getFloor());
		found.setRoomStatus(body.getRoomStatus());

		return roomRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Room found = this.findById(id);
		roomRepository.delete(found);
	}

}
