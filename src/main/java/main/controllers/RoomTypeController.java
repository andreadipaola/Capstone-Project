package main.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

import main.entities.RoomType;
import main.payloads.RoomTypePayload;
import main.services.RoomTypeService;

@RestController
@RequestMapping("/roomTypes")
public class RoomTypeController {
	@Autowired
	private RoomTypeService roomTypeService;

	@GetMapping("")
	@PreAuthorize("hasAuthority('GUEST')")
	public Page<RoomType> getAllRoomTypes(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "name") String sortBy)
			throws Exception {
		return roomTypeService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public RoomType saveRoomType(@RequestBody @Validated RoomTypePayload body) throws Exception {
		return roomTypeService.create(body);
	}

	@GetMapping("/{id}")
	public RoomType getRoomType(@PathVariable UUID id) throws Exception {
		return roomTypeService.findById(id);
	}

	@PutMapping("/{id}")
	public RoomType updateRoomType(@PathVariable UUID id, @RequestBody @Validated RoomTypePayload body)
			throws Exception {
		return roomTypeService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRoomType(@PathVariable UUID id) throws Exception {
		roomTypeService.findByIdAndDelete(id);
	}

}
