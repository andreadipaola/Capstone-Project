package main.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.entities.Invoice;
import main.exceptions.NotFoundException;
import main.payloads.InvoicePayload;
import main.repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	public Page<Invoice> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return invoiceRepository.findAll(pageable);
	}

	public Invoice create(InvoicePayload body) {
		Invoice invoice = new Invoice(body.getTotal());
		return invoiceRepository.save(invoice);
	}

	public Invoice findById(UUID id) throws NotFoundException {
		return invoiceRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'ospite cercato non è stato trovato!"));
	}

	public Invoice findByIdAndUpdate(UUID id, InvoicePayload body) throws NotFoundException {
		Invoice found = this.findById(id);

		found.setInvoiceId(id);
		found.setTotal(body.getTotal());

		return invoiceRepository.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Invoice found = this.findById(id);
		invoiceRepository.delete(found);
	}

}
