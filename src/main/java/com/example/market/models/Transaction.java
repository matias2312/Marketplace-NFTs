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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Setter(AccessLevel.NONE)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime date;
    public Transaction(Client client, TransactionType type, Double amount, String description, LocalDateTime date) {
        this.client = client;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
}
