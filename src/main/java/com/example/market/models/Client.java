package com.example.market.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<ClientProduct> clientProducts = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Sale> lastBuys = new HashSet<>();

    @Column(name = "enabled")
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Double balance;
    private Boolean active;

    @ElementCollection
    @Column(name = "Favorites")
    private List<Long> favorites = new ArrayList<>();

    @ElementCollection
    @Column(name = "Created")
    private List<Long> created = new ArrayList<>();
    private boolean verified;

    public Client(){}

    public Client(String firstName, String lastName, String email, String password, Double balance, Boolean active, boolean verified){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.active = true;
        this.verified = verified;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void addCart(ClientProduct clientProduct){
        clientProduct.setClient(this);
        clientProducts.add(clientProduct);
    }

    public void removeCart(ClientProduct clientProduct){
        clientProducts.remove(clientProduct);
    }

    public Set<ClientProduct> getClientProducts() {
        return clientProducts;
    }

    public void setClientProducts(Set<ClientProduct> clientProducts) {
        this.clientProducts = clientProducts;
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

    public List<Long> getCreated() {
        return created;
    }

    public void setCreated(List<Long> created) {
        this.created = created;
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
