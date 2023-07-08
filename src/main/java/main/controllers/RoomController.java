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

import main.entities.Room;
import main.payloads.RoomPayload;
import main.services.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
	@Autowired
	private RoomService roomService;

	@GetMapping("")
	public Page<Room> getAllRooms(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "roomNumber") String sortBy)
			throws Exception {
		return roomService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Room saveRoom(@RequestBody @Validated RoomPayload body) throws Exception {
		return roomService.create(body);
	}

	@GetMapping("/{id}")
	public Room getRoom(@PathVariable UUID id) throws Exception {
		return roomService.findById(id);
	}

	@PutMapping("/{id}")
	public Room updateRoom(@PathVariable UUID id, @RequestBody @Validated RoomPayload body) throws Exception {
		return roomService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRoom(@PathVariable UUID id) throws Exception {
		roomService.findByIdAndDelete(id);
	}

}
