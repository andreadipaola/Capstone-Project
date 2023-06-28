package main.entities;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import main.entities.enums.Role;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {
	@Id
	@GeneratedValue
	private UUID personId;
	private String fistName;
	private String lastName;
	private String email;
	private String password;
	public String phone;
	private Role role;
}
