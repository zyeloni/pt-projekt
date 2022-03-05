package me.kacperlukasik.klptl2.repository;

import me.kacperlukasik.klptl2.models.MedicalVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Long> {
        List<MedicalVisit> findByPetNameContaining(String petName);
}
