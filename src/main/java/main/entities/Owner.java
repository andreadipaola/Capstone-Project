package main.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.enums.Gender;
import main.entities.enums.Role;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
public class Owner extends Person {
	@OneToMany(mappedBy = "owner")
	private List<Hotel> hotels = new ArrayList<>();

	public Owner(Gender gender, String fistName, String lastName, String language, LocalDate dateOfBirth,
			String countryOfBirth, String cityOfBirth, String countryOfResidence, String cityOfResidence,
			String citizenship, String documentType, String documentNumber, String email, String password,
			String phone) {
		super(gender, fistName, lastName, language, dateOfBirth, countryOfBirth, cityOfBirth, countryOfResidence,
				cityOfResidence, citizenship, documentType, documentNumber, email, password, phone);
		this.setRole(Role.OWNER);
	}

	public Owner(Gender gender, String fistName, String lastName, String language, LocalDate dateOfBirth,
			String countryOfBirth, String cityOfBirth, String countryOfResidence, String cityOfResidence,
			String citizenship, String documentType, String documentNumber, String email, String password, String phone,
			List<Hotel> hotels) {
		super(gender, fistName, lastName, language, dateOfBirth, countryOfBirth, cityOfBirth, countryOfResidence,
				cityOfResidence, citizenship, documentType, documentNumber, email, password, phone);
		this.hotels = hotels;
		this.setRole(Role.OWNER);
	}

}
