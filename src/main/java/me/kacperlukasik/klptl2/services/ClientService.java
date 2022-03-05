package me.kacperlukasik.klptl2.services;

import me.kacperlukasik.klptl2.models.Client;
import me.kacperlukasik.klptl2.models.MedicalVisit;

import java.util.List;

public interface ClientService {
    public abstract Client get(Long id);
    public abstract void remove(Long id);
    public abstract void create(Client client);
    public abstract List<Client> findAll();
}
