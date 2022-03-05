package me.kacperlukasik.klptl2.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kacperlukasik.klptl2.models.Client;
import me.kacperlukasik.klptl2.models.Drug;
import me.kacperlukasik.klptl2.models.MedicalVisit;
import me.kacperlukasik.klptl2.repository.ClientRepository;
import me.kacperlukasik.klptl2.repository.DrugRepository;
import me.kacperlukasik.klptl2.repository.MedicalVisitRepository;
import me.kacperlukasik.klptl2.services.ClientService;
import me.kacperlukasik.klptl2.services.DrugService;
import me.kacperlukasik.klptl2.services.MedicalVisitService;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/visits")
@AllArgsConstructor
@Log4j2
public class MedicalVisitController {

    private final MedicalVisitService medicalVisitService;
    private final ClientService clientService;
    private final DrugService drugService;

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("medicalVisit/index");
        modelAndView.addObject("allVisits", medicalVisitService.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/details")
    public String details(@RequestParam(value = "id") Optional<MedicalVisit> medicalVisit, Model model) {
        ModelAndView modelAndView = new ModelAndView("medicalVisit/details");

        model.addAttribute("visit", medicalVisit.orElse(new MedicalVisit()));

        return "medicalVisit/details";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/showForm")
    public String showForm(@RequestParam(value = "id") Optional<MedicalVisit> medicalVisit, Model model) {
        model.addAttribute("visit", medicalVisit.orElse(new MedicalVisit()));
        log.log(Level.DEBUG, "Treść komunikatu logowanego np., na konsolę");
        return "medicalVisit/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "processForm")
    public String processForm(@ModelAttribute("visit") MedicalVisit model, MultipartFile multipartFile, BindingResult result) throws Exception {
        if (result.hasErrors())
            return "medicalVisit/form";

        medicalVisitService.create(model, multipartFile);

        return "medicalVisit/successVisitFormView";
    }

    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public ModelAndView delete(@ModelAttribute("id") Long id) throws Exception {
        medicalVisitService.remove(id);

        return new ModelAndView("redirect:/visits/index");
    }

    @ModelAttribute("clients")
    public List<Client> loadClients() throws Exception {
        return clientService.findAll();
    }

    @ModelAttribute("drugs")
    public List<Drug> loadDrugs() throws Exception {
        return drugService.findAll();
    }
}
