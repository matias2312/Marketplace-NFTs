package com.example.market.Services.ImplementationServices;

import com.example.market.Services.ClientProductService;
import com.example.market.models.Client;
import com.example.market.models.ClientProduct;
import com.example.market.repositories.ClientProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientProductImpl implements ClientProductService {
    @Autowired
    private ClientProductRepository clientProductRepository;

    @Override
    public ClientProduct getClientProductById(Long Id){
        return clientProductRepository.findById(Id).get();
    }
   @Override
    public void deleteItem(ClientProduct clientProduct){
        clientProductRepository.delete(clientProduct);
   }
   @Override
    public void saveItem(ClientProduct clientProduct){
        clientProductRepository.save(clientProduct);
   }

}
