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

import main.entities.Receptionist;
import main.payloads.ReceptionistPayload;
import main.services.ReceptionistService;

@RestController
@RequestMapping("/receptionists")
public class ReceptionistController {
	@Autowired
	private ReceptionistService receptionistService;

	@GetMapping("")
	public Page<Receptionist> getAllReceptionists(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "lastName") String sortBy)
			throws Exception {
		return receptionistService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Receptionist saveReceptionist(@RequestBody @Validated ReceptionistPayload body) throws Exception {
		return receptionistService.create(body);
	}

	@GetMapping("/{id}")
	public Receptionist getReceptionist(@PathVariable UUID id) throws Exception {
		return receptionistService.findById(id);
	}

	@PutMapping("/{id}")
	public Receptionist updateReceptionist(@PathVariable UUID id, @RequestBody @Validated ReceptionistPayload body)
			throws Exception {
		return receptionistService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReceptionist(@PathVariable UUID id) throws Exception {
		receptionistService.findByIdAndDelete(id);
	}

}
