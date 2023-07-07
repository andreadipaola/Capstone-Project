package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	@OneToMany(mappedBy = "hotel")
	private List<Room> rooms = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	@OneToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;
	@OneToMany(mappedBy = "hotel")
	private List<Receptionist> receptionist = new ArrayList<>();

	public Hotel(String name, String address, String city, String country, List<Room> rooms, Owner owner,
			Manager manager, List<Receptionist> receptionist) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.country = country;
		this.rooms = rooms;
		this.owner = owner;
		this.manager = manager;
		this.receptionist = receptionist;
	}

}
