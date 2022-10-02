package com.example.market.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClientProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;
    private boolean isActive;
    private LocalDateTime dateAdd;
    public ClientProduct(Client client, Product product) {
        this.client = client;
        this.product = product;
        this.dateAdd = LocalDateTime.now();
        this.isActive = true;
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
