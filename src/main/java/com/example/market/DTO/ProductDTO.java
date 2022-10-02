package com.example.market.DTO;

import com.example.market.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private LocalDate creationDate;
    private Boolean active;
    private String image;
    private boolean sell;
    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.creationDate = product.getCreationDate();
        this.active = product.getActive();
        this.image = product.getImage();
        this.sell = product.isSell();
    }
}
