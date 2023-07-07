package main.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.enums.Gender;
import main.entities.enums.Role;

@Entity
@Table(name = "managers")
@Getter
@Setter
@NoArgsConstructor
public class Manager extends Person {
	@OneToOne(mappedBy = "manager")
	private Hotel hotel;

	public Manager(Gender gender, String fistName, String lastName, String language, LocalDate dateOfBirth,
			String countryOfBirth, String cityOfBirth, String countryOfResidence, String cityOfResidence,
			String citizenship, String documentType, String documentNumber, String email, String password,
			String phone) {
		super(gender, fistName, lastName, language, dateOfBirth, countryOfBirth, cityOfBirth, countryOfResidence,
				cityOfResidence, citizenship, documentType, documentNumber, email, password, phone);
		this.setRole(Role.MANAGER);
	}

	public Manager(Gender gender, String fistName, String lastName, String language, LocalDate dateOfBirth,
			String countryOfBirth, String cityOfBirth, String countryOfResidence, String cityOfResidence,
			String citizenship, String documentType, String documentNumber, String email, String password, String phone,
			Hotel hotel) {
		super(gender, fistName, lastName, language, dateOfBirth, countryOfBirth, cityOfBirth, countryOfResidence,
				cityOfResidence, citizenship, documentType, documentNumber, email, password, phone);
		this.hotel = hotel;
		this.setRole(Role.MANAGER);
	}

}