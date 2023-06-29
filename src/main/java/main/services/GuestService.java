package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Guest;
import main.exceptions.NotFoundException;
import main.payloads.GuestPayload;
import main.repositories.GuestRepository;

@Service
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;

	public Page<Guest> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return guestRepository.findAll(pageable);
	}

	public Guest create(GuestPayload body) {
		Guest guest = new Guest(body.getFistName(), body.getLastName(), body.getEmail(), body.getPassword(),
				body.getPhone(), body.getCreditCard());
		return guestRepository.save(guest);
	}

	public Guest findById(UUID id) throws NotFoundException {
		return guestRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'ospite cercato non è stato trovato!"));
	}

	public Guest findByIdAndUpdate(UUID id, GuestPayload body) throws NotFoundException {
		Guest found = this.findById(id);

		found.setPersonId(id);
		found.setFistName(body.getFistName());
		found.setLastName(body.getLastName());
		found.setEmail(body.getEmail());
		found.setPassword(body.getPassword());
		found.setPhone(body.getPhone());
		found.setCreditCard(body.getCreditCard());

		return guestRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Guest found = this.findById(id);
		guestRepository.delete(found);
	}

}
