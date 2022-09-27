package com.example.market.controllers;

import com.example.market.DTO.NewProductDTO;
import com.example.market.DTO.ProductDTO;
import com.example.market.Services.ClientService;
import com.example.market.Services.ProductService;
import com.example.market.models.Client;
import com.example.market.models.Product;
import com.example.market.repositories.ClientRepository;
import com.example.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;

    @PostMapping("/clients/current/product/create")
    public ResponseEntity<Object> newProduct(Authentication authentication, @RequestBody NewProductDTO newProductDTO){
        Client client = clientService.findByClientEmail(authentication.getName());

        if(client == null){
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }

        if(newProductDTO.getName().isEmpty() || newProductDTO.getDescription().isEmpty() || newProductDTO.getPrice() == null || newProductDTO.getImage().isEmpty()){
            return new ResponseEntity<>("Miising data", HttpStatus.FORBIDDEN);
        }
        else {
            Product newProduct = new Product(client, newProductDTO.getName(), newProductDTO.getDescription(), newProductDTO.getPrice(), LocalDate.now(), true, newProductDTO.getImage(), false);
            productService.saveProduct(newProduct);
            client.addCreated(newProduct.getId());
            clientService.saveClient(client);
            return new ResponseEntity<>("Succesfully created" + newProduct.getId() + " ", HttpStatus.CREATED);
        }
    }

    @PatchMapping("/clients/current/product/delete")
    public ResponseEntity<Object> deleteProduct(@RequestParam Long productId,  Authentication authentication){

        Client client = clientService.findByClientEmail(authentication.getName());
        Product product = productService.getProductById(productId);

        if(client == null){
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }
        if(product == null){
            return new ResponseEntity<>("Product does not exist", HttpStatus.FORBIDDEN);
        }
        if(!client.getProducts().contains(product)){
            return new ResponseEntity<>("Product does not belong to the customer", HttpStatus.FORBIDDEN);
        }
        product.setActive(false);
        productService.saveProduct(product);
        return new ResponseEntity<>("Product deleted",HttpStatus.CREATED);
    }

    @PatchMapping("/clients/current/product/sell")
    public ResponseEntity<Object> sellProduct(@RequestParam Long productId,  Authentication authentication,
                                                @RequestParam String description, @RequestParam Double price){

        Client client = clientService.findByClientEmail(authentication.getName());
        Product product = productService.getProductById(productId);

        if(client == null){
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }
        if(product == null){
            return new ResponseEntity<>("Product does not exist", HttpStatus.FORBIDDEN);
        }

        if(!client.getProducts().contains(product)){
            return new ResponseEntity<>("Product does not belong to the customer", HttpStatus.FORBIDDEN);
        }

        product.setDescription(description);
        product.setPrice(price);
        product.setSell(true);
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/clients/current/product/edit")
    public ResponseEntity<Object> editProduct(@RequestParam Long productId,  Authentication authentication,
                                              @RequestParam String description, @RequestParam Double price, @RequestParam boolean sell){

        Client client = clientService.findByClientEmail(authentication.getName());
        Product product = productService.getProductById(productId);

        if(client == null){
            return new ResponseEntity<>("Client does not exist", HttpStatus.FORBIDDEN);
        }
        if(product == null){
            return new ResponseEntity<>("Product does not exist", HttpStatus.FORBIDDEN);
        }

        if(!client.getProducts().contains(product)){
            return new ResponseEntity<>("Product does not belong to the customer", HttpStatus.FORBIDDEN);
        }
        product.setSell(sell);
        product.setDescription(description);
        product.setPrice(price);
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products")
    public List<ProductDTO> getShop(){
           return productService.getProducts().stream().filter(product -> product.isSell()).map(product -> new ProductDTO(product)).collect(Collectors.toList());
    }

}
