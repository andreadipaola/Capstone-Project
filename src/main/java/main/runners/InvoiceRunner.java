package main.runners;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import main.entities.Invoice;
import main.repositories.InvoiceRepository;

@Order(5)
@Component
public class InvoiceRunner implements CommandLineRunner {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public void run(String... args) throws Exception {

		if (invoiceRepository.count() == 0) {
			for (int i = 0; i < 50; i++) {
				try {
					double randomNumber = ThreadLocalRandom.current().nextDouble(70, 3000);
					Double total = Math.round(randomNumber * 100.0) / 100.0;

					Invoice invoice = new Invoice(total, null, null);
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
