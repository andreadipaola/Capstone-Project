package main.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import main.entities.enums.Role;

@Getter
@Setter
public class UserPayload {
	private String avatar;
	@NotNull(message = "ATTENZIONE!!! Il campo First Name è obbligatorio")
	private String firstName;
	@NotNull(message = "ATTENZIONE!!! Il campo Last Name è obbligatorio")
	private String lastName;
	@NotNull(message = "ATTENZIONE!!! Il campo Email è obbligatorio")
	private String email;
//	@NotNull(message = "ATTENZIONE!!! Il campo Password è obbligatorio")
	private String password;
	private Role role;
}
