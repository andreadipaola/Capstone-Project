package main.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import main.entities.enums.Role;

public abstract class PersonPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo First Name è obbligatorio")
	private String fistName;
	@NotNull(message = "ATTENZIONE!!! Il campo Last Name è obbligatorio")
	private String lastName;
	@NotNull(message = "ATTENZIONE!!! Il campo Email è obbligatorio")
	@Email(message = "ATTENZIONE!!! Inserire un indirizzo email valido")
	private String email;
	@NotNull(message = "ATTENZIONE!!! Il campo Password è obbligatorio")
	@Size(min = 8, max = 20, message = "ATTENZIONE!!! la password deve essere minimo di 8 caratteri e massimo di 20")
	private String password;
	public String phone;
	private Role role;
}
