package main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, UUID> {

}
