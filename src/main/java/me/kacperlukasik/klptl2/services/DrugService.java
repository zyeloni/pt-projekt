package me.kacperlukasik.klptl2.services;

import me.kacperlukasik.klptl2.models.Drug;
import me.kacperlukasik.klptl2.models.MedicalVisit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DrugService {
    public abstract Drug get(Long id);
    public abstract void remove(Long id);
    public abstract void create(Drug medicalVisit);
    public abstract List<Drug> findAll();
}
