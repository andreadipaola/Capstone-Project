package main.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "guests")
@Getter
@Setter
@NoArgsConstructor
public class Guest extends Person {
	private String creditCard;
	@OneToMany(mappedBy = "guest")
	private List<Reservation> reservations = new ArrayList<>();
	@OneToMany(mappedBy = "guest")
	private List<Review> reviews = new ArrayList<>();

}
