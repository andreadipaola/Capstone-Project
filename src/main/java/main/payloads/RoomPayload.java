package main.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import main.entities.enums.RoomStatus;

@Getter
@Setter
public class RoomPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Room Number è obbligatorio")
	private String roomNumber;
	@NotNull(message = "ATTENZIONE!!! Il campo Floor è obbligatorio")
	private String floor;
	@NotNull(message = "ATTENZIONE!!! Il campo Room Status è obbligatorio")
	private RoomStatus roomStatus;
	@NotNull(message = "ATTENZIONE!!! Il campo Is Smooking è obbligatorio")
	private boolean isSmoking;
}
