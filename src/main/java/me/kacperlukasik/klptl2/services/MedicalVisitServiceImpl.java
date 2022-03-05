package me.kacperlukasik.klptl2.services;

import lombok.AllArgsConstructor;
import me.kacperlukasik.klptl2.models.MedicalVisit;
import me.kacperlukasik.klptl2.repository.MedicalVisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("medicalVisit")
@AllArgsConstructor
public class MedicalVisitServiceImpl implements MedicalVisitService {

    public MedicalVisitRepository medicalVisitRepository;


    @Override
    public MedicalVisit get(Long id) {
        return medicalVisitRepository.getById(id);
    }

    @Override
    public void remove(Long id) {
        medicalVisitRepository.deleteById(id);
    }

    @Override
    public void create(MedicalVisit medicalVisit, MultipartFile multipartFile) {
        if(multipartFile.isEmpty() == false) {
            try {
                medicalVisit.setFileContent(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        medicalVisitRepository.save(medicalVisit);
    }

    @Override
    public List<MedicalVisit> findAll() {
        return medicalVisitRepository.findAll();
    }
}
