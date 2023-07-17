package main.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.enums.RoomStatus;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
public class Room {
	@Id
	@GeneratedValue
	@Column(name = "room_id")
	private UUID roomId;
	@Column(name = "room_number")
	private String roomNumber;
	private String floor;
	@Column(name = "room_status")
	@Enumerated(EnumType.STRING)
	private RoomStatus roomStatus;

	@ManyToOne
	@JoinColumn(name = "room_type_id")
	@JsonBackReference
	private RoomType roomType;

	@Column(name = "date_added")
	private LocalDate dateAdded;

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	@JsonBackReference
	private Reservation reservation;

	public Room(String roomNumber, String floor, RoomStatus roomStatus, RoomType roomType, Reservation reservation) {
		this.roomNumber = roomNumber;
		this.floor = floor;
		this.roomStatus = roomStatus;
		this.roomType = roomType;
		this.dateAdded = LocalDate.now();
		this.reservation = reservation;
	}

}
