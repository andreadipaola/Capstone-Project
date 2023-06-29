package main.entities;

import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.enums.Role;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class Person {
	@Id
	@GeneratedValue
	private UUID personId;
	private String fistName;
	private String lastName;
	private String email;
	private String password;
	public String phone;
	@Enumerated(EnumType.STRING)
	private Role role = Role.GUEST;

	public Person(String fistName, String lastName, String email, String password, String phone) {
		this.fistName = fistName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

}
