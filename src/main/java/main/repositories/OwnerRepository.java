package main.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {
	Optional<Owner> findByEmail(String email);
}
