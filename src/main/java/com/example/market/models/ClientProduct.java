package com.example.market.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ClientProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    private boolean isActive;

    private LocalDateTime dateAdd;

    public ClientProduct() {
    }

    public ClientProduct(Client client, Product product) {
        this.client = client;
        this.product = product;
        this.dateAdd = LocalDateTime.now();
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getDateAdded() {
        return dateAdd;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdd = dateAdded;
    }

    @Override
    public String toString() {
        return "ClientProduct{" +
                "id=" + id +
                ", client=" + client +
                ", product=" + product +
                ", dateAdd=" + dateAdd +
                '}';
    }
}
