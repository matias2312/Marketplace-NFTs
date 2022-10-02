package com.example.market.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewProductDTO {
    private String name;
    private String description;
    private Double price;
    private String image;
    public NewProductDTO(String name, String description, Double price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

}
