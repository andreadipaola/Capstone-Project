package main.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Name è obbligatorio")
	private String name;
	@NotNull(message = "ATTENZIONE!!! Il campo Address è obbligatorio")
	private String Address;
}
