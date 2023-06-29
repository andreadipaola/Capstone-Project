package main.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
	public Page<Guest> getAllGuests(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "lastName") String sortBy)
			throws Exception {
		return guestService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Guest saveGuest(@RequestBody @Validated GuestPayload body) throws Exception {
		return guestService.create(body);
	}

	@GetMapping("/{guestId}")
	public Guest getGuest(@PathVariable UUID guestId) throws Exception {
		return guestService.findById(guestId);
	}

	@PutMapping("/{gestId}")
	public Guest updateGuest(@PathVariable UUID gestId, @RequestBody @Validated GuestPayload body) throws Exception {
//		if (gestId == null) {
//			throw new IllegalArgumentException("L'ID dell' ospite non può essere nullo");
//		}
		return guestService.findByIdAndUpdate(gestId, body);
	}

	@DeleteMapping("/{gestId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGuest(@PathVariable UUID gestId) throws Exception {
		guestService.findByIdAndDelete(gestId);
	}

}
