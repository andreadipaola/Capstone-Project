package main.payloads;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import main.entities.enums.Gender;

@Getter
@Setter
public class GuestPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Gender è obbligatorio")
	private Gender gender;
	@NotNull(message = "ATTENZIONE!!! Il campo First Name è obbligatorio")
	private String firstName;
	@NotNull(message = "ATTENZIONE!!! Il campo Last Name è obbligatorio")
	private String lastName;
	@NotNull(message = "ATTENZIONE!!! Il campo Language è obbligatorio")
	private String language;
	@NotNull(message = "ATTENZIONE!!! Il campo Date Of Birth è obbligatorio")
	private LocalDate dateOfBirth;
	@NotNull(message = "ATTENZIONE!!! Il campo Country Of Birth è obbligatorio")
	private String countryOfBirth;
	@NotNull(message = "ATTENZIONE!!! Il campo City Of Birth è obbligatorio")
	private String cityOfBirth;
	@NotNull(message = "ATTENZIONE!!! Il campo Country Of Residence è obbligatorio")
	private String countryOfResidence;
	@NotNull(message = "ATTENZIONE!!! Il campo City Of Residence è obbligatorio")
	private String cityOfResidence;
	@NotNull(message = "ATTENZIONE!!! Il campo Citizenship è obbligatorio")
	private String citizenship;
	@NotNull(message = "ATTENZIONE!!! Il campo Document Type è obbligatorio")
	private String documentType;
	@NotNull(message = "ATTENZIONE!!! Il campo Document Number è obbligatorio")
	private String documentNumber;
	@NotNull(message = "ATTENZIONE!!! Il campo Email è obbligatorio")
	@Email(message = "ATTENZIONE!!! Inserire un indirizzo email valido")
	private String email;
	@NotNull(message = "ATTENZIONE!!! Il campo Password è obbligatorio")
	@Size(min = 8, max = 20, message = "ATTENZIONE!!! la password deve essere minimo di 8 caratteri e massimo di 20")
	private String password;
	@NotNull(message = "ATTENZIONE!!! Il campo Phone è obbligatorio")
	public String phone;
	private String note;
	private String foodIntolerance;
	private String creditCard;
	private String reasonOfTheTrip;
}
