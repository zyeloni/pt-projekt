package me.kacperlukasik.klptl2.services;

import me.kacperlukasik.klptl2.models.MedicalVisit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MedicalVisitService {
    public abstract MedicalVisit get(Long id);
    public abstract void remove(Long id);
    public abstract void create(MedicalVisit medicalVisit, MultipartFile multipartFile);
    public abstract List<MedicalVisit> findAll();
}
