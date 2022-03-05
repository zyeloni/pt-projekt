package me.kacperlukasik.klptl2.repository;

import me.kacperlukasik.klptl2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
