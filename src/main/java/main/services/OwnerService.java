package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Owner;
import main.exceptions.NotFoundException;
import main.payloads.OwnerPayload;
import main.repositories.OwnerRepository;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository ownerRepository;

	public Page<Owner> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return ownerRepository.findAll(pageable);
	}

	public Owner create(OwnerPayload body) {
		Owner owner = new Owner(body.getGender(), body.getFirstName(), body.getLastName(), body.getLanguage(),
				body.getDateOfBirth(), body.getCountryOfBirth(), body.getCityOfBirth(), body.getCountryOfResidence(),
				body.getCityOfResidence(), body.getCistizenship(), body.getDocumentType(), body.getDocumentNumber(),
				body.getEmail(), body.getPassword(), body.getPhone());
		return ownerRepository.save(owner);
	}

	public Owner findById(UUID id) throws NotFoundException {
		return ownerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'ospite cercato non è stato trovato!"));
	}

	public Owner findByIdAndUpdate(UUID id, OwnerPayload body) throws NotFoundException {
		Owner found = this.findById(id);

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

		return ownerRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Owner found = this.findById(id);
		ownerRepository.delete(found);
	}

}
