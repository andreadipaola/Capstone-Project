package main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {

}
