package main.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Rating è obbligatorio")
	private double rating;
	@NotNull(message = "ATTENZIONE!!! Il campo Comment è obbligatorio")
	private String comment;
}
