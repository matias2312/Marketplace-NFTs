package com.example.market.DTO;

import com.example.market.models.Product;

import java.time.LocalDate;

public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private LocalDate creationDate;
    private Boolean active;
    private String image;

    private boolean sell;

    public ProductDTO(){}

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public String getImage() {
        return image;
    }

    public boolean isSell() {
        return sell;
    }
}
