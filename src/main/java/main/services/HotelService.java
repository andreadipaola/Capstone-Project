package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Hotel;
import main.exceptions.NotFoundException;
import main.payloads.HotelPayload;
import main.repositories.HotelRepository;
import main.repositories.OwnerRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	public Page<Hotel> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return hotelRepository.findAll(pageable);
	}

	public Hotel create(HotelPayload body) {
//		Optional<Owner> optionaOwner = ownerRepository.findByEmail("andr3a.dipaola@gmail.com");
//		Owner owner = optionaOwner.get();
		Hotel hotel = new Hotel(body.getName(), body.getAddress(), body.getCity(), body.getCountry(), null, null, null,
				null);
		return hotelRepository.save(hotel);
	}

	public Hotel findById(UUID id) throws NotFoundException {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'ospite cercato non è stato trovato!"));
	}

	public Hotel findByIdAndUpdate(UUID id, HotelPayload body) throws NotFoundException {
		Hotel found = this.findById(id);

		found.setHotelId(id);
		found.setName(body.getName());
		found.setAddress(body.getAddress());

		return hotelRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Hotel found = this.findById(id);
		hotelRepository.delete(found);
	}

}
