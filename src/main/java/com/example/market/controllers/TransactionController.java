package com.example.market.controllers;


import com.example.market.Services.ClientService;
import com.example.market.Services.ProductService;
import com.example.market.Services.TransactionService;
import com.example.market.models.*;
import com.example.market.repositories.ClientRepository;
import com.example.market.repositories.ProductRepository;
import com.example.market.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;

    @Transactional
    @PostMapping("/clients/current/transaction")
    public ResponseEntity<Object> newBuy(Authentication authentication){
        Client client = clientService.findByClientEmail(authentication.getName());
        Set<ClientProduct> cart = client.getClientProducts().stream().filter(ClientProduct::isActive).collect(Collectors.toSet());
        double totalPrice = client.getClientProducts().stream().map(clientProduct -> clientProduct.getProduct().getPrice()).reduce((double) 0, Double::sum);

        if(client == null){
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }
        if(client.getClientProducts() == null){
            return new ResponseEntity<>("Don't have products in cart", HttpStatus.FORBIDDEN);
        }
        if(client.getBalance() < totalPrice){
            return new ResponseEntity<>("Don't have enough funds", HttpStatus.FORBIDDEN);
        }

        else {
            cart.stream().forEach(clientProduct ->{
                Product itemToExchange = clientProduct.getProduct();
                Client owner = itemToExchange.getClient();
                itemToExchange.setClient(client);
                Transaction transaction = new Transaction(client, TransactionType.BUY, itemToExchange.getPrice(), "gracias", LocalDateTime.now());
                Transaction transaction1 = new Transaction(owner, TransactionType.SELL, itemToExchange.getPrice(), "oli", LocalDateTime.now());
                productService.saveProduct(itemToExchange);
                transactionService.saveTransaction(transaction);
                transactionService.saveTransaction(transaction1);
                client.setBalance(client.getBalance() - itemToExchange.getPrice());
                owner.setBalance(owner.getBalance() + itemToExchange.getPrice());
            });
            return new ResponseEntity<>("Adquire new nfts",HttpStatus.CREATED);
        }
    }
}
