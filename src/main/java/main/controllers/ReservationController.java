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

import main.entities.Reservation;
import main.payloads.ReservationPayload;
import main.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	@Autowired
	private ReservationService reservationService;

	@GetMapping("")
	public Page<Reservation> getAllReservations(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "arrivalDate") String sortBy)
			throws Exception {
		return reservationService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation saveReservation(@RequestBody @Validated ReservationPayload body) throws Exception {
		return reservationService.create(body);
	}

	@GetMapping("/{id}")
	public Reservation getReservation(@PathVariable UUID id) throws Exception {
		return reservationService.findById(id);
	}

	@PutMapping("/{id}")
	public Reservation updateReservation(@PathVariable UUID id, @RequestBody @Validated ReservationPayload body)
			throws Exception {
		return reservationService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReservation(@PathVariable UUID id) throws Exception {
		reservationService.findByIdAndDelete(id);
	}

}
