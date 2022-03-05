package me.kacperlukasik.klptl2.repository;

import me.kacperlukasik.klptl2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByUsername(String username);
}
