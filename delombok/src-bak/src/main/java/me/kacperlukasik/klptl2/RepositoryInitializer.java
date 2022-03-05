package me.kacperlukasik.klptl2;

import me.kacperlukasik.klptl2.models.Client;
import me.kacperlukasik.klptl2.models.Drug;
import me.kacperlukasik.klptl2.models.MedicalVisit;
import me.kacperlukasik.klptl2.repository.ClientRepository;
import me.kacperlukasik.klptl2.repository.DrugRepository;
import me.kacperlukasik.klptl2.repository.MedicalVisitRepository;
import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class RepositoryInitializer {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MedicalVisitRepository medicalVisitRepository;
    @Autowired
    private DrugRepository drugRepository;

    @Bean
    InitializingBean init() {
        return () -> {
            if (clientRepository.findAll().isEmpty()) {
                Client client = new Client();
                client.setDateOfBirth(LocalDate.now());
                client.setName("ELO");

                clientRepository.save(client);
            }

            if (medicalVisitRepository.findAll().isEmpty()) {
                MedicalVisit medicalVisit = new MedicalVisit();
                medicalVisit.setClient(clientRepository.findAll().get(0));
                medicalVisit.setVisitCost((float) 20.99);
                medicalVisit.setConfirmed(true);
                medicalVisit.setVisitDate((LocalDate.now()));
                medicalVisitRepository.save(medicalVisit);
            }

            if (drugRepository.findAll().isEmpty()){
                Drug drug = new Drug();
                drug.setName("Insulina");
                drugRepository.save(drug);

                Drug drug1 = new Drug();
                drug1.setName("Morfina");
                drugRepository.save(drug1);

                Drug drug2 = new Drug();
                drug2.setName("Antybiotyk");
                drugRepository.save(drug2);
            }
        };
    }
}
