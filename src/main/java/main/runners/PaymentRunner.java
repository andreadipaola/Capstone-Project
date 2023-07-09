package main.runners;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import main.entities.Invoice;
import main.entities.Payment;
import main.entities.enums.PaymentStatus;
import main.repositories.InvoiceRepository;
import main.repositories.PaymentRepository;

@Order(8)
@Component
public class PaymentRunner implements CommandLineRunner {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public void run(String... args) throws Exception {
		Random random = new Random();
		List<Invoice> invoicesFromDB = invoiceRepository.findAll();

		if (paymentRepository.count() == 0) {
			for (int i = 0; i < 20; i++) {
				try {
					PaymentStatus paymentStatus = getRandomEnumValue(PaymentStatus.class);
					LocalDateTime paymentDateTime = generateRandomDateTime(random);
					int randomIvoicesIndex = random.nextInt(invoicesFromDB.size());
					Invoice invoice = invoicesFromDB.get(randomIvoicesIndex);
					invoicesFromDB.remove(randomIvoicesIndex);
					Payment payment = new Payment(paymentStatus, paymentDateTime, invoice);

					paymentRepository.save(payment);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			System.out.println("Nel DB della struttura sono presenti " + paymentRepository.count() + " pagamenti");
		} else {
			System.out.println("Nel DB della struttura sono presenti " + paymentRepository.count() + " pagamenti");
		}
	}

	public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();
		Random random = new Random();
		int index = random.nextInt(values.length);
		return values[index];
	}

	private static LocalDateTime generateRandomDateTime(Random random) {
		// Definizione dell'intervallo di date desiderato
		LocalDateTime minDateTime = LocalDateTime.of(2018, 1, 1, 0, 0);
		LocalDateTime maxDateTime = LocalDateTime.now();

		// Calcolo della differenza in secondi tra le due date
		long minSeconds = minDateTime.toEpochSecond(java.time.ZoneOffset.UTC);
		long maxSeconds = maxDateTime.toEpochSecond(java.time.ZoneOffset.UTC);
		long randomSeconds = minSeconds + random.nextLong() % (maxSeconds - minSeconds);

		// Creazione dell'oggetto LocalDateTime casuale
		return LocalDateTime.ofEpochSecond(randomSeconds, 0, java.time.ZoneOffset.UTC);
	}

}
