package me.kacperlukasik.klptl2.repository;

import me.kacperlukasik.klptl2.models.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
