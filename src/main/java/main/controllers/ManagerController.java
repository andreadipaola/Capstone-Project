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

import main.entities.Manager;
import main.payloads.ManagerPayload;
import main.services.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {
	@Autowired
	private ManagerService managerService;

	@GetMapping("")
	public Page<Manager> getAllManagers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "lastName") String sortBy)
			throws Exception {
		return managerService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Manager saveManager(@RequestBody @Validated ManagerPayload body) throws Exception {
		return managerService.create(body);
	}

	@GetMapping("/{id}")
	public Manager getManager(@PathVariable UUID id) throws Exception {
		return managerService.findById(id);
	}

	@PutMapping("/{id}")
	public Manager updateManager(@PathVariable UUID id, @RequestBody @Validated ManagerPayload body) throws Exception {
		return managerService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteManager(@PathVariable UUID id) throws Exception {
		managerService.findByIdAndDelete(id);
	}

}
