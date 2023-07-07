package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Payment;
import main.exceptions.NotFoundException;
import main.payloads.PaymentPayload;
import main.repositories.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public Page<Payment> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return paymentRepository.findAll(pageable);
	}

	public Payment create(PaymentPayload body) {
		Payment payment = new Payment(body.getPaymentStatus(), body.getPaymentDateTime());
		return paymentRepository.save(payment);
	}

	public Payment findById(UUID id) throws NotFoundException {
		return paymentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'ospite cercato non è stato trovato!"));
	}

	public Payment findByIdAndUpdate(UUID id, PaymentPayload body) throws NotFoundException {
		Payment found = this.findById(id);

		found.setPaymentId(id);
		found.setPaymentStatus(body.getPaymentStatus());
		found.setPaymentDateTime(body.getPaymentDateTime());

		return paymentRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Payment found = this.findById(id);
		paymentRepository.delete(found);
	}

}
