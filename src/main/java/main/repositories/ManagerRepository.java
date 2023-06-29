package main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, UUID> {

}
