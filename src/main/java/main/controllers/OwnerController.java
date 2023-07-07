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

import main.entities.Owner;
import main.payloads.OwnerPayload;
import main.services.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@GetMapping("")
	public Page<Owner> getAllOwners(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "lastName") String sortBy)
			throws Exception {
		return ownerService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Owner saveOwner(@RequestBody @Validated OwnerPayload body) throws Exception {
		return ownerService.create(body);
	}

	@GetMapping("/{id}")
	public Owner getOwner(@PathVariable UUID id) throws Exception {
		return ownerService.findById(id);
	}

	@PutMapping("/{id}")
	public Owner updateOwner(@PathVariable UUID id, @RequestBody @Validated OwnerPayload body) throws Exception {
		return ownerService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOwner(@PathVariable UUID id) throws Exception {
		ownerService.findByIdAndDelete(id);
	}

}
