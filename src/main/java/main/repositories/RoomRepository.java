package main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

}
