package com.example.market.DTO;

import com.example.market.models.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Getter
@NoArgsConstructor
public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<ProductDTO> product;
    private Double balance;
    private Set<TransactionDTO> transactions;
    private Boolean active;
    private List<Long> favorites ;
    private List<Long> created ;
    private Set <ClientProductDTO> clientProduct;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.product = client.getProducts().stream().map(ProductDTO::new).collect(Collectors.toSet());
        this.balance = client.getBalance();
        this.active = client.getActive();
        this.transactions = client.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        this.favorites = client.getFavorites();
        this.created = client.getCreated();
        this.clientProduct = client.getClientProducts().stream().map(ClientProductDTO::new).collect(Collectors.toSet());
    }

}
