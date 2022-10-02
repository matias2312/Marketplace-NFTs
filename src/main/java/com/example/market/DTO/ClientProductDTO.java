package com.example.market.DTO;

import com.example.market.models.ClientProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class ClientProductDTO {

    private Long id;

    private String clientName;

    private ProductDTO product;

    private LocalDateTime dateAdd;

    public ClientProductDTO(ClientProduct clientProduct) {
        this.id = clientProduct.getId();
        this.clientName = clientProduct.getClient().getFirstName();
        this.product = new ProductDTO(clientProduct.getProduct());
        this.dateAdd = clientProduct.getDateAdded();
    }
}
