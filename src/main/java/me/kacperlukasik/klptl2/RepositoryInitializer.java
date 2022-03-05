package me.kacperlukasik.klptl2;

import me.kacperlukasik.klptl2.models.*;
import me.kacperlukasik.klptl2.repository.*;
import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class RepositoryInitializer {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MedicalVisitRepository medicalVisitRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private RoleRepository roleRepositorys;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

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


            if (userRepository.findAll().isEmpty())
            {
                Role roleUser = roleRepositorys.save(new Role(Role.Types.ROLE_USER));
                Role roleAdmin = roleRepositorys.save(new Role(Role.Types.ROLE_ADMIN));

                roleRepositorys.save(roleUser);
                roleRepositorys.save(roleAdmin);



                User user = new User("user", true);
                user.setRoles(new HashSet<Role>(Arrays.asList(roleUser)));
                user.setPassword(passwordEncoder.encode("user"));

                User admin = new User("admin", true);
                admin.setRoles(new HashSet<Role>(Arrays.asList(roleAdmin)));
                admin.setPassword(passwordEncoder.encode("admin"));

                User test = new User("superuser", true);
                test.setRoles(new HashSet<Role>(Arrays.asList(roleAdmin, roleUser)));
                test.setPassword(passwordEncoder.encode("superuser"));

                userRepository.save(user);
                userRepository.save(admin);
                userRepository.save(test);

            }
        };
    }
}
