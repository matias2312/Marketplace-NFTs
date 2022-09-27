package com.example.market.DTO;

import com.example.market.models.Sale;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class SaleDTO {
    private long id;

    private ClientDTO client;

    private Set<ProductDTO> cart;

    private Double price;

    private LocalDate creationDate;

    private Boolean active;

    public SaleDTO() {
    }

    public SaleDTO(Sale sale) {
        this.id = sale.getId();
        this.client = new ClientDTO(sale.getClient());
        this.cart = sale.getCart().stream().map(product -> new ProductDTO(product)).collect(Collectors.toSet());
        this.price = sale.getPrice();
        this.creationDate = sale.getCreationDate();
        this.active = sale.getActive();
    }

    public long getId() {
        return id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public Set<ProductDTO> getCart() {
        return cart;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Boolean getActive() {
        return active;
    }
}
