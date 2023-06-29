package main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID> {

}
