package com.example.market.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    private List<ClientProduct> cartsContaining ;

    private String name;
    private String description;
    private Double price;
    private LocalDate creationDate;
    private Boolean active;
    private String image;
    private boolean sell;

    public Product(Client client, String name, String description, Double price, LocalDate creationDate, Boolean active, String image, boolean sell) {
        this.client = client;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.active = active;
        this.image = image;
        this.sell = sell;
    }

    public void addCart(ClientProduct clientProduct){
        clientProduct.setProduct(this);
        cartsContaining.add(clientProduct);
    }

    public void removeCart(ClientProduct clientProduct){
        cartsContaining.remove(clientProduct);
    }
}
