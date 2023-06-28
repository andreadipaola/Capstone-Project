package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	private UUID roomTypeId;
	private String name;
	private String initials;
	private String description;
	private double price;
	private int capacity;
	@OneToMany(mappedBy = "roomType")
	private List<Room> rooms = new ArrayList<>();
}
