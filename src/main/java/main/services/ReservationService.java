package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Guest;
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
		Guest guest = body.getGuest();
		Reservation reservation = new Reservation(body.getArrivalDate(), body.getDepartureDate(),
				body.getBookingStatus(), guest, null);

		return reservationRepository.save(reservation);
	}

	public Reservation findById(UUID id) throws NotFoundException {
		return reservationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! La prenotazione cercata non è stata trovata!"));
	}

//	public Reservation findByIdAndUpdate(UUID id, ReservationPayload body) throws NotFoundException {
//		Reservation found = this.findById(id);
//
//		found.setReservationId(id);
//		found.setArrivalDate(body.getArrivalDate());
//		found.setDepartureDate(body.getDepartureDate());
//		found.setBookingStatus(body.getBookingStatus());
//
//		if (body.getGuest() != null) {
//			Guest guest = found.getGuest();
//			guest.setFirstName(body.getGuest().getFirstName());
//			guest.setLastName(body.getGuest().getLastName());
//			guest.setCitizenship(body.getGuest().getCitizenship());
//			guest.setEmail(body.getGuest().getEmail());
//			guest.setPhone(body.getGuest().getPhone());
//			guest.setNote(body.getGuest().getNote());
//		}
//		
//
//
//		return reservationRepository.save(found);
//	}

	public Reservation findByIdAndUpdate(UUID id, ReservationPayload body) throws NotFoundException {
		Reservation found = this.findById(id);

		found.setReservationId(id);

		// Copia solo i campi forniti da body a found
		if (body.getArrivalDate() != null) {
			found.setArrivalDate(body.getArrivalDate());
		}
		if (body.getDepartureDate() != null) {
			found.setDepartureDate(body.getDepartureDate());
		}
		if (body.getBookingStatus() != null) {
			found.setBookingStatus(body.getBookingStatus());
		}

		// Gestisci i campi Guest
		if (body.getGuest() != null) {
			Guest guest = found.getGuest();
			Guest bodyGuest = body.getGuest();

			if (bodyGuest.getFirstName() != null) {
				guest.setFirstName(bodyGuest.getFirstName());
			}
			if (bodyGuest.getLastName() != null) {
				guest.setLastName(bodyGuest.getLastName());
			}
			if (bodyGuest.getCitizenship() != null) {
				guest.setCitizenship(bodyGuest.getCitizenship());
			}
			if (bodyGuest.getEmail() != null) {
				guest.setEmail(bodyGuest.getEmail());
			}
			if (bodyGuest.getPhone() != null) {
				guest.setPhone(bodyGuest.getPhone());
			}
			if (bodyGuest.getNote() != null) {
				guest.setNote(bodyGuest.getNote());
			}
			// Puoi continuare con gli altri campi Guest se ce ne sono altri
		}

//	    // Gestisci i campi Invoice
//	    if (body.getInvoice() != null) {
//	        Invoice invoice = found.getInvoice();
//	        Invoice bodyInvoice = body.getInvoice();
//
//	        if (bodyInvoice.getTotal() != null) {
//	            invoice.setTotal(bodyInvoice.getTotal());
//	        }
//	        // Puoi continuare con gli altri campi Invoice se ce ne sono altri
//	    }

		// Resto del codice...

		return reservationRepository.save(found);
	}

//	public Reservation updateReservation(UUID id, Reservation reservation) throws NotFoundException {
//		Reservation found = this.findById(id);
//
//		found.setArrivalDate(reservation.getArrivalDate());
//		found.setDepartureDate(reservation.getDepartureDate());
//		found.setBookingStatus(reservation.getBookingStatus());
//
//		Guest guest = found.getGuest();
//		guest.setFirstName(reservation.getGuest().getFirstName());
//		guest.setLastName(reservation.getGuest().getLastName());
//		guest.setEmail(reservation.getGuest().getEmail());
//
//		return reservationRepository.save(found);
//	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Reservation found = this.findById(id);
		reservationRepository.delete(found);
	}

}
