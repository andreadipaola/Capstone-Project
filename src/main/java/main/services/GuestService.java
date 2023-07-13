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
		Guest guest = new Guest(body.getGender(), body.getFirstName(), body.getLastName(), body.getLanguage(),
				body.getDateOfBirth(), body.getCountryOfBirth(), body.getCityOfBirth(), body.getCountryOfResidence(),
				body.getCityOfResidence(), body.getCitizenship(), body.getDocumentType(), body.getDocumentNumber(),
				body.getEmail(), body.getPassword(), body.getPhone(), body.getNote(), body.getFoodIntolerance(),
				body.getCreditCard(), body.getReasonOfTheTrip(), null);
		return guestRepository.save(guest);
	}

	public Guest findById(UUID id) throws NotFoundException {
		return guestRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'ospite cercato non è stato trovato!"));
	}

	public Guest findByIdAndUpdate(UUID id, GuestPayload body) throws NotFoundException {
		Guest found = this.findById(id);

		found.setGuestId(id);
		found.setGender(body.getGender());
		found.setFirstName(body.getFirstName());
		found.setLastName(body.getLastName());
		found.setLanguage(body.getLanguage());
		found.setDateOfBirth(body.getDateOfBirth());
		found.setCountryOfBirth(body.getCountryOfBirth());
		found.setCityOfBirth(body.getCityOfBirth());
		found.setCountryOfResidence(body.getCountryOfResidence());
		found.setCityOfResidence(body.getCityOfResidence());
		found.setCitizenship(body.getCitizenship());
		found.setDocumentType(body.getDocumentType());
		found.setDocumentNumber(body.getDocumentNumber());
		found.setEmail(body.getEmail());
		found.setPassword(body.getPassword());
		found.setPhone(body.getPhone());
		found.setNote(body.getNote());
		found.setFoodIntolerance(body.getFoodIntolerance());
		found.setCreditCard(body.getCreditCard());
		found.setReasonOfTheTrip(body.getReasonOfTheTrip());

		return guestRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Guest found = this.findById(id);
		guestRepository.delete(found);
	}

}
