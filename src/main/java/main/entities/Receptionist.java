package main.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.enums.Gender;
import main.entities.enums.Role;

@Entity
@Table(name = "receptionists")
@Getter
@Setter
@NoArgsConstructor
public class Receptionist extends Person {
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	public Receptionist(Gender gender, String fistName, String lastName, String language, LocalDate dateOfBirth,
			String countryOfBirth, String cityOfBirth, String countryOfResidence, String cityOfResidence,
			String citizenship, String documentType, String documentNumber, String email, String password,
			String phone) {
		super(gender, fistName, lastName, language, dateOfBirth, countryOfBirth, cityOfBirth, countryOfResidence,
				cityOfResidence, citizenship, documentType, documentNumber, email, password, phone);
		this.setRole(Role.RECEPTIONIST);
	}

	public Receptionist(Gender gender, String fistName, String lastName, String language, LocalDate dateOfBirth,
			String countryOfBirth, String cityOfBirth, String countryOfResidence, String cityOfResidence,
			String citizenship, String documentType, String documentNumber, String email, String password, String phone,
			Hotel hotel) {
		super(gender, fistName, lastName, language, dateOfBirth, countryOfBirth, cityOfBirth, countryOfResidence,
				cityOfResidence, citizenship, documentType, documentNumber, email, password, phone);
		this.hotel = hotel;
		this.setRole(Role.RECEPTIONIST);
	}

}
