package main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.Receptionist;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, UUID> {

}
