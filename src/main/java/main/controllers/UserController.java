package main.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import main.entities.User;
import main.payloads.UserPayload;
import main.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder bcrypt;

	@GetMapping("")
	@PreAuthorize("hasAuthority('GUEST')")
	public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "lastName") String sortBy)
			throws Exception {
		return userService.find(page, size, sortBy);
	}

	@PostMapping("")
	@PreAuthorize("hasAuthority('GUEST')")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Validated UserPayload body) throws Exception {

		return userService.create(body);
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable UUID id) throws Exception {
		return userService.findById(id);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable UUID id, @RequestBody @Validated UserPayload body) throws Exception {
		body.setPassword(bcrypt.encode(body.getPassword()));

		return userService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID id) throws Exception {
		userService.findByIdAndDelete(id);
	}

}
