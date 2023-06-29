package main.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestPayload extends PersonPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Credit Card è obbligatorio")
	private String creditCard;
}
