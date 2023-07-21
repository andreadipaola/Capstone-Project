package main.payloads;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import main.entities.enums.PaymentStatus;

@Getter
@Setter
public class PaymentPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Payment Status è obbligatorio")
	PaymentStatus paymentStatus;
	@NotNull(message = "ATTENZIONE!!! Il campo Payment Date Time è obbligatorio")
	LocalDateTime paymentDateTime;
}
