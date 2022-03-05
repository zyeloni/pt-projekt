package me.kacperlukasik.klptl2.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.kacperlukasik.klptl2.models.Client;
import me.kacperlukasik.klptl2.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clientService")
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    ClientRepository clientRepository;

    @Override
    public Client get(Long id) {
        return clientRepository.getById(id);
    }

    @Override
    public void remove(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
