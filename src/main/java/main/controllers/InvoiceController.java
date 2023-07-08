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

import main.entities.Invoice;
import main.payloads.InvoicePayload;
import main.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("")
	public Page<Invoice> getAllInvoices(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "total") String sortBy)
			throws Exception {
		return invoiceService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Invoice saveInvoice(@RequestBody @Validated InvoicePayload body) throws Exception {
		return invoiceService.create(body);
	}

	@GetMapping("/{id}")
	public Invoice getInvoice(@PathVariable UUID id) throws Exception {
		return invoiceService.findById(id);
	}

	@PutMapping("/{id}")
	public Invoice updateInvoice(@PathVariable UUID id, @RequestBody @Validated InvoicePayload body) throws Exception {
		return invoiceService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInvoice(@PathVariable UUID id) throws Exception {
		invoiceService.findByIdAndDelete(id);
	}

}
