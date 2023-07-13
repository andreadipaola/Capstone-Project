package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.User;
import main.exceptions.BadRequestException;
import main.exceptions.NotFoundException;
import main.payloads.UserPayload;
import main.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Page<User> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return userRepository.findAll(pageable);
	}

	public User create(UserPayload body) {
		userRepository.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException(
					"ATTENZIONE!!! L'email con la quale stai cercando di registarti è già in uso da un altro utente");
		});
		User user = new User(body.getFirstName(), body.getLastName(), body.getEmail(), body.getPassword());
		return userRepository.save(user);
	}

	public User findById(UUID id) throws NotFoundException {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! La struttura cercata non è stata trovata!"));
	}

	public User findByIdAndUpdate(UUID id, UserPayload body) throws NotFoundException {
		User found = this.findById(id);

		found.setUserId(id);
		found.setFirstName(body.getFirstName());
		found.setLastName(body.getLastName());
		found.setEmail(body.getEmail());
		found.setPassword(body.getPassword());

		return userRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		userRepository.delete(found);
	}

	public User findByEmail(String email) throws NotFoundException {
		return userRepository.findByEmail(email).orElseThrow(
				() -> new NotFoundException("ATTENZIONE!!! L'email che stai cercando non è stata trovata"));
	}

}
