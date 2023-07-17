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

import main.entities.Payment;
import main.payloads.PaymentPayload;
import main.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@GetMapping("")
	public Page<Payment> getAllPayments(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "paymentDateTime") String sortBy)
			throws Exception {
		return paymentService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Payment savePayment(@RequestBody @Validated PaymentPayload body) throws Exception {
		return paymentService.create(body);
	}

	@GetMapping("/{id}")
	public Payment getPayment(@PathVariable UUID id) throws Exception {
		return paymentService.findById(id);
	}

	@PutMapping("/{id}")
	public Payment updatePayment(@PathVariable UUID id, @RequestBody @Validated PaymentPayload body) throws Exception {
		return paymentService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePayment(@PathVariable UUID id) throws Exception {
		paymentService.findByIdAndDelete(id);
	}

}
