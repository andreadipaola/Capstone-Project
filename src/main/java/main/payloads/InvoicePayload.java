package main.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoicePayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Total è obbligatorio")
	private double total;
}
