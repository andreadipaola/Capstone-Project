package main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

}
