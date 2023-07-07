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
@Table(name = "guests")
@Getter
@Setter
@NoArgsConstructor
public class Guest extends Person {
	private String note;
	private String foodIntolerance;
	private String creditCard;
	private String reasonOfTheTrip;
	@OneToMany(mappedBy = "guest")
	private List<Reservation> reservations = new ArrayList<>();

	public Guest(Gender gender, String firstName, String lastName, String language, LocalDate dateOfBirth,
			String countryOfBirth, String cityOfBirth, String countryOfResidence, String cityOfResidence,
			String citizenship, String documentType, String documentNumber, String email, String password, String phone,
			String note, String foodIntolerance, String creditCard, String reasonOfTheTrip,
			List<Reservation> reservations) {
		super(gender, firstName, lastName, language, dateOfBirth, countryOfBirth, cityOfBirth, countryOfResidence,
				cityOfResidence, citizenship, documentType, documentNumber, email, password, phone);
		this.note = note;
		this.foodIntolerance = foodIntolerance;
		this.creditCard = creditCard;
		this.reasonOfTheTrip = reasonOfTheTrip;
		this.reservations = reservations;
		this.setRole(Role.GUEST);
	}

}
