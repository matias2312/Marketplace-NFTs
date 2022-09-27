package com.example.market.Services.ImplementationServices;

import com.example.market.Services.UserService;
import com.example.market.emails.EmailServiceImpl;
import com.example.market.models.Client;
import com.example.market.models.ClientProduct;
import com.example.market.repositories.ClientProductRepository;
import com.example.market.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Autowired
    private ClientProductRepository clientProductRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public boolean sendInvoice(Client user, byte[] bytes) {
        String email = emailServiceImpl.createEmail(user.getFirstName(), user.getLastName());
        emailServiceImpl.send(user.getEmail(), email, bytes);
        return true;
    }

    @Override
    public void clearCart(Client client) {
        clientProductRepository.deleteAll(client.getClientProducts());
        Set<ClientProduct> cleanSet = new HashSet<>();
        client.setClientProducts(cleanSet);
        clientRepository.save(client);
    }

}
