package main.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

}
