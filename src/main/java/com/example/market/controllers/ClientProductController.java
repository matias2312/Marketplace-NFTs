package com.example.market.controllers;


import com.example.market.DTO.ClientProductDTO;

import com.example.market.Services.ClientProductService;
import com.example.market.Services.ClientService;
import com.example.market.Services.ImplementationServices.UserServiceImpl;
import com.example.market.Services.ProductService;
import com.example.market.models.Client;
import com.example.market.models.ClientProduct;
import com.example.market.models.Product;
import com.example.market.repositories.ClientProductRepository;
import com.example.market.repositories.ClientRepository;
import com.example.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientProductController {
    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;
    @Autowired
    ClientProductService clientProductService;
    @Autowired
    private UserServiceImpl userService;

    @PatchMapping("/clients/current/cart")
    public ResponseEntity<Object> addToCart(Authentication authentication,@RequestParam Long productId){
        Client client = clientService.findByClientEmail(authentication.getName());
        Product product = productService.getProductById(productId);
        ClientProduct clientProduct = new ClientProduct(client, product);
        Set<Long> cartProducts = client.getClientProducts().stream().map(clientProduc ->  clientProduc.getProduct().getId()).collect(Collectors.toSet());
        if(client == null){
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }
        if(product == null){
            return new ResponseEntity<>("Product does not exist", HttpStatus.FORBIDDEN);
        }
        if(client.getProducts().contains(product)){
            return new ResponseEntity<>("You cannot add your own product to the cart", HttpStatus.FORBIDDEN);
        }
        if(cartProducts.contains(productId)){
            return new ResponseEntity<>("already added to cart", HttpStatus.FORBIDDEN);
        }
        client.addCart(clientProduct);
        clientProductService.saveItem(clientProduct);
        return new ResponseEntity<>("Added to cart",HttpStatus.OK);
    }

    @GetMapping("/clients/current/cart")
    public Set<ClientProductDTO> getCart (Authentication authentication){
        Client client = clientService.findByClientEmail(authentication.getName());
        return client.getClientProducts().stream().filter(clientProduct -> clientProduct.getProduct().isSell()).map(ClientProductDTO::new).collect(Collectors.toSet());
    }
    @PatchMapping("/clients/current/cart/remove")
    public ResponseEntity<Object> deleteItem(Authentication authentication, @RequestParam Long clientProductId){
        Client client = clientService.findByClientEmail(authentication.getName());
        ClientProduct clientProduct = clientProductService.getClientProductById(clientProductId);
        Product product = productService.getProductById(clientProduct.getProduct().getId());

        if(client == null){
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }
        if(product == null){
            return new ResponseEntity<>("Product does not exist", HttpStatus.FORBIDDEN);
        }
        else {
            clientProduct.setActive(false);
            clientProductService.deleteItem(clientProduct);
            return new ResponseEntity<>("Removed from cart",HttpStatus.OK);
        }
    }

    @DeleteMapping("/clients/current/cart/delete")
    public ResponseEntity<Object> deleteCart(Authentication authentication){
        Client client = clientService.findByClientEmail(authentication.getName());

        if(client == null){
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }
        else {
            userService.clearCart(client);
            return new ResponseEntity<>("Cart Cleared",HttpStatus.OK);
        }
    }

    @PatchMapping("/clients/current/favorite")
    public ResponseEntity<Object> favoriteItem(Authentication authentication, @RequestParam Long productId) {
        Client client = clientService.findByClientEmail(authentication.getName());
        Product product = productService.getProductById(productId);

        if (client == null) {
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }
        if (product == null) {
            return new ResponseEntity<>("Product does not exist", HttpStatus.FORBIDDEN);
        }
        if (client.getFavorites().contains(productId)) {
            client.removeFavorites(productId);
            clientService.saveClient(client);
            return new ResponseEntity<>("Product removed", HttpStatus.OK);
        } else {
            client.addFavorites(productId);
            clientService.saveClient(client);
            return new ResponseEntity<>("Added to favorites", HttpStatus.OK);
        }
    }
}
