package com.example.market.Services.ImplementationServices;

import com.example.market.DTO.ClientDTO;
import com.example.market.Services.ClientService;
import com.example.market.models.Client;
import com.example.market.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }
    @Override
    public Client getClientById(Long Id){
        return clientRepository.findById(Id).get();
    }
    @Override
    public Client findByClientEmail(String email){
        return clientRepository.findByEmail(email);
    }
    @Override
    public void saveClient(Client client){
        clientRepository.save(client);
    }
}
