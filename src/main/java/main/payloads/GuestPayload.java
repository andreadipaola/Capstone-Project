package main.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestPayload extends PersonPayload {
	private String note;
	private String foodIntolerance;
	private String creditCard;
	private String reasonOfTheTrip;
}
