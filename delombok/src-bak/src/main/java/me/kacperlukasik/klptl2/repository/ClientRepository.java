package me.kacperlukasik.klptl2.repository;

import me.kacperlukasik.klptl2.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
