package main.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypePayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Name è obbligatorio")
	private String name;
	@NotNull(message = "ATTENZIONE!!! Il campo Initials è obbligatorio")
	private String initials;
	@NotNull(message = "ATTENZIONE!!! Il campo Description è obbligatorio")
	private String description;
	@NotNull(message = "ATTENZIONE!!! Il campo Price è obbligatorio")
	private double price;
	@NotNull(message = "ATTENZIONE!!! Il campo Capacity è obbligatorio")
	private int capacity;
}
