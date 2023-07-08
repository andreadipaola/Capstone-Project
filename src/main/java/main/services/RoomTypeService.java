package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.RoomType;
import main.exceptions.NotFoundException;
import main.payloads.RoomTypePayload;
import main.repositories.RoomTypeRepository;

@Service
public class RoomTypeService {

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	public Page<RoomType> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return roomTypeRepository.findAll(pageable);
	}

	public RoomType create(RoomTypePayload body) {
		RoomType roomType = new RoomType(body.getName(), body.getInitials(), body.getDescription(), body.getPrice(),
				body.getCapacity(), null);
		return roomTypeRepository.save(roomType);
	}

	public RoomType findById(UUID id) throws NotFoundException {
		return roomTypeRepository.findById(id).orElseThrow(
				() -> new NotFoundException("ATTENZIONE!!! Il tipo di camera cercato non è stato trovato!"));
	}

	public RoomType findByIdAndUpdate(UUID id, RoomTypePayload body) throws NotFoundException {
		RoomType found = this.findById(id);

		found.setRoomTypeId(id);
		found.setName(body.getName());
		found.setInitials(body.getInitials());
		found.setDescription(body.getDescription());
		found.setPrice(body.getPrice());
		found.setCapacity(body.getCapacity());

		return roomTypeRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		RoomType found = this.findById(id);
		roomTypeRepository.delete(found);
	}

}
