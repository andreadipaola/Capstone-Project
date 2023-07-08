package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Receptionist;
import main.exceptions.NotFoundException;
import main.payloads.ReceptionistPayload;
import main.repositories.ReceptionistRepository;

@Service
public class ReceptionistService {

	@Autowired
	private ReceptionistRepository receptionistRepository;

	public Page<Receptionist> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return receptionistRepository.findAll(pageable);
	}

	public Receptionist create(ReceptionistPayload body) {
		Receptionist receptionist = new Receptionist(body.getGender(), body.getFirstName(), body.getLastName(),
				body.getLanguage(), body.getDateOfBirth(), body.getCountryOfBirth(), body.getCityOfBirth(),
				body.getCountryOfResidence(), body.getCityOfResidence(), body.getCistizenship(), body.getDocumentType(),
				body.getDocumentNumber(), body.getEmail(), body.getPassword(), body.getPhone(), null);
		return receptionistRepository.save(receptionist);
	}

	public Receptionist findById(UUID id) throws NotFoundException {
		return receptionistRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! Il receptionist cercato non è stato trovato!"));
	}

	public Receptionist findByIdAndUpdate(UUID id, ReceptionistPayload body) throws NotFoundException {
		Receptionist found = this.findById(id);

		found.setPersonId(id);
		found.setGender(body.getGender());
		found.setFirstName(body.getFirstName());
		found.setLastName(body.getLastName());
		found.setLanguage(body.getLanguage());
		found.setDateOfBirth(body.getDateOfBirth());
		found.setCountryOfBirth(body.getCountryOfBirth());
		found.setCityOfBirth(body.getCityOfBirth());
		found.setCountryOfResidence(body.getCountryOfResidence());
		found.setCityOfResidence(body.getCityOfResidence());
		found.setCitizenship(body.getCistizenship());
		found.setDocumentType(body.getDocumentType());
		found.setDocumentNumber(body.getDocumentNumber());
		found.setEmail(body.getEmail());
		found.setPassword(body.getPassword());
		found.setPhone(body.getPhone());

		return receptionistRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Receptionist found = this.findById(id);
		receptionistRepository.delete(found);
	}

}
