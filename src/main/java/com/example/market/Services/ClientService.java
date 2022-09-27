package com.example.market.Services;

import com.example.market.models.Client;

import java.util.List;

public interface ClientService {

    public List<Client> getClients();
    public Client getClientById(Long Id);
    public Client findByClientEmail(String email);
    public void saveClient(Client client);
}
