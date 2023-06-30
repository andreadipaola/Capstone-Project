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
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "lastName") String sortBy)
			throws Exception {
		return hotelService.find(page, size, sortBy);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Hotel saveHotel(@RequestBody @Validated HotelPayload body) throws Exception {
		return hotelService.create(body);
	}

	@GetMapping("/{hotelId}")
	public Hotel getHotel(@PathVariable UUID hotelId) throws Exception {
		return hotelService.findById(hotelId);
	}

	@PutMapping("/{gestId}")
	public Hotel updateHotel(@PathVariable UUID gestId, @RequestBody @Validated HotelPayload body) throws Exception {
		return hotelService.findByIdAndUpdate(gestId, body);
	}

	@DeleteMapping("/{gestId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteHotel(@PathVariable UUID gestId) throws Exception {
		hotelService.findByIdAndDelete(gestId);
	}

}
