package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Reservation;
import main.exceptions.NotFoundException;
import main.payloads.ReservationPayload;
import main.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	public Page<Reservation> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return reservationRepository.findAll(pageable);
	}

	public Reservation create(ReservationPayload body) {
		Reservation reservation = new Reservation(body.getArrivalDate(), body.getDepartureDate(),
				body.getBookingStatus(), null, null, null);
		return reservationRepository.save(reservation);
	}

	public Reservation findById(UUID id) throws NotFoundException {
		return reservationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! La prenotazione cercata non è stata trovata!"));
	}

	public Reservation findByIdAndUpdate(UUID id, ReservationPayload body) throws NotFoundException {
		Reservation found = this.findById(id);

		found.setReservationId(id);
		found.setArrivalDate(body.getArrivalDate());
		found.setDepartureDate(body.getDepartureDate());

		return reservationRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Reservation found = this.findById(id);
		reservationRepository.delete(found);
	}

}
