package com.example.market.DTO;

import com.example.market.models.ClientProduct;

import java.time.LocalDateTime;

public class ClientProductDTO {

    private Long id;

    private String clientName;

    private ProductDTO product;

    private LocalDateTime dateAdd;

    public ClientProductDTO() {
    }

    public ClientProductDTO(ClientProduct clientProduct) {
        this.id = clientProduct.getId();
        this.clientName = clientProduct.getClient().getFirstName();
        this.product =new ProductDTO(clientProduct.getProduct());
        this.dateAdd = clientProduct.getDateAdded();
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public LocalDateTime getDateAdd() {
        return dateAdd;
    }
}
