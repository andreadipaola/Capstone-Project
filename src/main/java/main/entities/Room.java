package main.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private RoomStatus roomStatus;
	@Column(name = "is_smoking")
	private boolean isSmoking;
	@ManyToOne
	@JoinColumn(name = "room_type_id")
	private RoomType roomType;
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

}
