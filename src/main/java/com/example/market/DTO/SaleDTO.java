package com.example.market.DTO;

import com.example.market.models.Sale;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SaleDTO {
    private long id;
    private ClientDTO client;
    private Set<ProductDTO> cart;
    private Double price;
    private LocalDate creationDate;
    private Boolean active;
    public SaleDTO(Sale sale) {
        this.id = sale.getId();
        this.client = new ClientDTO(sale.getClient());
        this.cart = sale.getCart().stream().map(product -> new ProductDTO(product)).collect(Collectors.toSet());
        this.price = sale.getPrice();
        this.creationDate = sale.getCreationDate();
        this.active = sale.getActive();
    }
}
