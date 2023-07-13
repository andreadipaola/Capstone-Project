package main.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
public class Hotel {
	@Id
	@GeneratedValue
	@Column(name = "hotel_id")
	private UUID hotelId;
	private String name;
	private String address;
	private String city;
	private String country;

	public Hotel(String name, String address, String city, String country) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.country = country;
	}

}
