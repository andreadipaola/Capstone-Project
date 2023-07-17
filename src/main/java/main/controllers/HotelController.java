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

import main.entities.Hotel;
import main.payloads.HotelPayload;
import main.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelService hotelService;

	@GetMapping("")
	public Page<Hotel> getAllHotels(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "name") String sortBy)
			throws Exception {
		return hotelService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Hotel saveHotel(@RequestBody @Validated HotelPayload body) throws Exception {
		return hotelService.create(body);
	}

	@GetMapping("/{id}")
	public Hotel getHotel(@PathVariable UUID id) throws Exception {
		return hotelService.findById(id);
	}

	@PutMapping("/{id}")
	public Hotel updateHotel(@PathVariable UUID id, @RequestBody @Validated HotelPayload body) throws Exception {
		return hotelService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteHotel(@PathVariable UUID id) throws Exception {
		hotelService.findByIdAndDelete(id);
	}

}
