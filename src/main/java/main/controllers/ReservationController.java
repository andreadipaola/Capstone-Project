package main.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import main.entities.Reservation;
import main.payloads.ReservationPayload;
import main.responses.ReservationResponse;
import main.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	@Autowired
	private ReservationService reservationService;

//	@GetMapping("")
//	@PreAuthorize("hasAuthority('MANAGER')")
//	public Page<Reservation> getAllReservations(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "arrivalDate") String sortBy)
//			throws Exception {
//		return reservationService.find(page, size, sortBy);
//	}

	@GetMapping("")
	@PreAuthorize("hasAuthority('MANAGER')")
	public Page<ReservationResponse> getAllReservations(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "50") int size, @RequestParam(defaultValue = "bookingStatus") String sortBy)
			throws Exception {
		Page<Reservation> reservationPage = reservationService.find(page, size, sortBy);
		List<Reservation> reservations = reservationPage.getContent();

		List<ReservationResponse> responseList = new ArrayList<>();
		for (Reservation reservation : reservations) {
			ReservationResponse response = new ReservationResponse();
			response.setReservationId(reservation.getReservationId());
			response.setArrivalDate(reservation.getArrivalDate());
			response.setDepartureDate(reservation.getDepartureDate());
			response.setBookingStatus(reservation.getBookingStatus());
			response.setGuest(reservation.getGuest());
			response.setRooms(reservation.getRooms());
//			response.setInvoice(reservation.getInvoice());
			responseList.add(response);
		}

		return new PageImpl<>(responseList, reservationPage.getPageable(), reservationPage.getTotalElements());
	}

	@PostMapping("")
	@PreAuthorize("hasAuthority('MANAGER')")
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation saveReservation(@RequestBody @Validated ReservationPayload body) throws Exception {
		return reservationService.create(body);
	}
//	
//	@PostMapping("")
//	@PreAuthorize("hasAuthority('MANAGER')")
//	@ResponseStatus(HttpStatus.CREATED)
//	public ReservationResponse saveReservation(@RequestBody @Validated ReservationPayload body) throws Exception {
//		Reservation reservation = reservationService.create(body);
//
//		ReservationResponse response = new ReservationResponse();
//		response.setReservationId(reservation.getReservationId());
//		response.setArrivalDate(reservation.getArrivalDate());
//		response.setDepartureDate(reservation.getDepartureDate());
//		response.setBookingStatus(reservation.getBookingStatus());
//		response.setGuest(reservation.getGuest());
//		response.setRooms(reservation.getRooms());
////		response.setInvoice(reservation.getInvoice());
//
//		return response;
//	}

//	@GetMapping("/{id}")
//	@PreAuthorize("hasAuthority('MANAGER')")
//	public Reservation getReservation(@PathVariable UUID id) throws Exception {
//		return reservationService.findById(id);
//	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('MANAGER')")
	public ReservationResponse getReservation(@PathVariable UUID id) throws Exception {
		Reservation reservation = reservationService.findById(id);

		ReservationResponse response = new ReservationResponse();
		response.setReservationId(reservation.getReservationId());
		response.setArrivalDate(reservation.getArrivalDate());
		response.setDepartureDate(reservation.getDepartureDate());
		response.setBookingStatus(reservation.getBookingStatus());
		response.setGuest(reservation.getGuest());
		response.setRooms(reservation.getRooms());
//		response.setInvoice(reservation.getInvoice());

		return response;
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('MANAGER')")
	public Reservation updateReservation(@PathVariable UUID id, @RequestBody @Validated ReservationPayload body)
			throws Exception {
		return reservationService.findByIdAndUpdate(id, body);

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('MANAGER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReservation(@PathVariable UUID id) throws Exception {
		reservationService.findByIdAndDelete(id);
	}

}
