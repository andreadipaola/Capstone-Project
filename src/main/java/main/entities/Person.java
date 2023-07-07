package main.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.enums.Gender;
import main.entities.enums.Role;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class Person {
	@Id
	@GeneratedValue
	private UUID personId;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String firstName;
	private String lastName;
	private String language;
	private LocalDate dateOfBirth;
	private String countryOfBirth;
	private String cityOfBirth;
	private String countryOfResidence;
	private String cityOfResidence;
	private String citizenship;
	private String documentType;
	private String documentNumber;
	private String email;
	private String password;
	private String phone;
	@Enumerated(EnumType.STRING)
	private Role role = Role.GUEST;

	public Person(Gender gender, String firstName, String lastName, String language, LocalDate dateOfBirth,
			String countryOfBirth, String cityOfBirth, String countryOfResidence, String cityOfResidence,
			String citizenship, String documentType, String documentNumber, String email, String password,
			String phone) {
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.language = language;
		this.dateOfBirth = dateOfBirth;
		this.countryOfBirth = countryOfBirth;
		this.cityOfBirth = cityOfBirth;
		this.countryOfResidence = countryOfResidence;
		this.cityOfResidence = cityOfResidence;
		this.citizenship = citizenship;
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

}
