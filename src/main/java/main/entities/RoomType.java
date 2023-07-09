package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "room_types")
@Getter
@Setter
@NoArgsConstructor
public class RoomType {
	@Id
	@GeneratedValue
	@Column(name = "room_type_id")
	private UUID roomTypeId;
	private String name;
	private String initials;
	private String description;
	private double price;
	private int capacity;

	@OneToMany(mappedBy = "roomType")
	private List<Room> rooms = new ArrayList<>();

	public RoomType(String name, String initials, String description, double price, int capacity, List<Room> rooms) {
		this.name = name;
		this.initials = initials;
		this.description = description;
		this.price = price;
		this.capacity = capacity;
		this.rooms = rooms;
	}

}
