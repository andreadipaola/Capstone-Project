package main.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

import main.entities.Guest;
import main.payloads.GuestPayload;
import main.services.GuestService;

@RestController
@RequestMapping("/guests")
public class GuestController {
	@Autowired
	private GuestService guestService;

	@GetMapping("")
	@PreAuthorize("hasAuthority('MANAGER')")
	public Page<Guest> getAllGuests(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "50") int size, @RequestParam(defaultValue = "lastName") String sortBy)
			throws Exception {
		return guestService.find(page, size, sortBy);
	}

	@PostMapping("")
	@PreAuthorize("hasAuthority('MANAGER')")
	@ResponseStatus(HttpStatus.CREATED)
	public Guest saveGuest(@RequestBody @Validated GuestPayload body) throws Exception {
		return guestService.create(body);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('MANAGER')")
	public Guest getGuest(@PathVariable UUID id) throws Exception {
		return guestService.findById(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('MANAGER')")
	public Guest updateGuest(@PathVariable UUID id, @RequestBody @Validated GuestPayload body) throws Exception {
//		if (gestId == null) {
//			throw new IllegalArgumentException("L'ID dell' ospite non può essere nullo");
//		}
		return guestService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('MANAGER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGuest(@PathVariable UUID id) throws Exception {
		guestService.findByIdAndDelete(id);
	}

}
