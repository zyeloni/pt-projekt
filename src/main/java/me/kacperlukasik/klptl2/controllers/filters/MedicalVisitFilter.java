package me.kacperlukasik.klptl2.controllers.filters;


import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kacperlukasik.klptl2.repository.MedicalVisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping(value = "/medicalVisit")
@AllArgsConstructor
public class MedicalVisitFilter {
    private final MedicalVisitRepository medicalVisitRepository;

    @GetMapping(value = "/search")
    public String searchByPetName(Model model, Optional<String> phrase) {
        model.addAttribute("allVisits", medicalVisitRepository.findByPetNameContaining(phrase.orElse("")));
        model.addAttribute("phrase", phrase);

        return "medicalVisit/index";
    }

}
