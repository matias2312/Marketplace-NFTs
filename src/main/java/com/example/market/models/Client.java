package com.example.market.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Setter(AccessLevel.NONE)
    private long id;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<ClientProduct> clientProducts = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Sale> lastBuys = new HashSet<>();
    @ElementCollection
    @Column(name = "Favorites")
    private List<Long> favorites = new ArrayList<>();

    @ElementCollection
    @Column(name = "Created")
    private List<Long> created = new ArrayList<>();
    @Column(name = "enabled")
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Double balance;
    private Boolean active;
    private boolean verified;

    public Client(String firstName, String lastName, String email, String password, Double balance, Boolean active, boolean verified){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.active = true;
        this.verified = verified;
    }
    public void addCart(ClientProduct clientProduct){
        clientProduct.setClient(this);
        clientProducts.add(clientProduct);
    }
    public void removeCart(ClientProduct clientProduct){
        clientProducts.remove(clientProduct);
    }
    public List<Long> getFavorites() {
        return favorites;
    }
    public void setFavorites(List<Long> favorites) {
        this.favorites = favorites;
    }
    public void addFavorites(Long favorite) {
        this.favorites.add(favorite);
    }
    public void removeFavorites(Long favorite) {
        this.favorites.remove(favorite);
    }
    public void addCreated(Long created) {
        this.created.add(created);
    }
    public void removeCreated(Long created) {
        this.created.remove(created);
    }
    public Set<Sale> getLastBuys() {
        return lastBuys;
    }
    public void addLastBuy(Sale sale) {
        this.lastBuys.add(sale);
    }
    public void setLastBuys(Set<Sale> lastBuys) {
        this.lastBuys = lastBuys;
    }
}
