package me.kacperlukasik.klptl2.services;

import lombok.AllArgsConstructor;
import me.kacperlukasik.klptl2.models.Drug;
import me.kacperlukasik.klptl2.repository.DrugRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("drugService")
public class DrugServiceImpl implements DrugService{
    DrugRepository drugRepository;

    @Override
    public Drug get(Long id) {
        return drugRepository.getById(id);
    }

    @Override
    public void remove(Long id) {
        drugRepository.deleteById(id);
    }

    @Override
    public void create(Drug medicalVisit) {
        drugRepository.save(medicalVisit);
    }

    @Override
    public List<Drug> findAll() {
        return drugRepository.findAll();
    }
}
