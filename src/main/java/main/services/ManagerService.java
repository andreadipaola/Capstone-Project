package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Manager;
import main.exceptions.NotFoundException;
import main.payloads.ManagerPayload;
import main.repositories.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	public Page<Manager> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return managerRepository.findAll(pageable);
	}

	public Manager create(ManagerPayload body) {
		Manager manager = new Manager(body.getGender(), body.getFirstName(), body.getLastName(), body.getLanguage(),
				body.getDateOfBirth(), body.getCountryOfBirth(), body.getCityOfBirth(), body.getCountryOfResidence(),
				body.getCityOfResidence(), body.getCistizenship(), body.getDocumentType(), body.getDocumentNumber(),
				body.getEmail(), body.getPassword(), body.getPhone());
		return managerRepository.save(manager);
	}

	public Manager findById(UUID id) throws NotFoundException {
		return managerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'ospite cercato non è stato trovato!"));
	}

	public Manager findByIdAndUpdate(UUID id, ManagerPayload body) throws NotFoundException {
		Manager found = this.findById(id);

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

		return managerRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Manager found = this.findById(id);
		managerRepository.delete(found);
	}

}
