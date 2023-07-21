package main.runners;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import main.entities.Invoice;
import main.repositories.InvoiceRepository;

@Order(7)
@Component
public class InvoiceRunner implements CommandLineRunner {

	@Autowired
	InvoiceRepository invoiceRepository;

//	@Autowired
//	ReservationRepository reservationRepository;

	@Override
	public void run(String... args) throws Exception {
		Random random = new Random();
//		List<Reservation> reservationsfromDB = reservationRepository.findAll();

		if (invoiceRepository.count() == 0) {
			for (int i = 0; i < 25; i++) {
				try {
					double randomNumber = ThreadLocalRandom.current().nextDouble(70, 3000);
					Double total = Math.round(randomNumber * 100.0) / 100.0;

//					int randomReservationsIndex = random.nextInt(reservationsfromDB.size());
//					Reservation reservation = reservationsfromDB.get(randomReservationsIndex);
//					reservationsfromDB.remove(randomReservationsIndex);

					Invoice invoice = new Invoice(total, null);
					invoiceRepository.save(invoice);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			System.out.println("Nel DB della struttura sono presenti " + invoiceRepository.count() + " fatture");
		} else {
			System.out.println("Nel DB della struttura sono presenti " + invoiceRepository.count() + " fatture");
		}
	}

}
