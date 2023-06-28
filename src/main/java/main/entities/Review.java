package main.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {
	@Id
	@GeneratedValue
	@Column(name = "review_id")
	private UUID reviewId;
	private double rating;
	private String comment;
	@ManyToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;
	@OneToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
}
