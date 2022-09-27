package com.example.market.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ElementCollection
    @Column(name = "purchases")
    private Set<Product> cart;

    private Double price;

    private LocalDate creationDate;

    private Boolean active;

    public Sale() {
    }

    public Sale(Client client) {
        this.client = client;
        this.cart = client.getClientProducts().stream().map(clientProduct -> clientProduct.getProduct()).collect(Collectors.toSet());
        double aux = client.getClientProducts().stream().map(clientProduct -> clientProduct.getProduct().getPrice()).reduce((double) 0,(a, b)->  a + b);
        this.price = aux;
        this.creationDate = LocalDate.now();
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setCart(Set<Product> cartsContaining) {
        this.cart = cartsContaining;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void addPrice(Double price) {
        this.price = this.price + price;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Product> getCart() {
        return cart;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
