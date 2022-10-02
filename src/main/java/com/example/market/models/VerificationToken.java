package com.example.market.models;

import javax.persistence.*;
import java.util.Date;

public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @OneToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "client_id")
    private Client client;
    private Date expiryDate;

    /*
     * private Date calculateExpiryDate(int expiryTimeInMinutes) {
     * Calendar cal = Calendar.getInstance();
     * cal.setTime(new Timestamp(cal.getTime().getTime()));
     * cal.add(Calendar.MINUTE, expiryTimeInMinutes);
     * return new Date(cal.getTime().getTime());
     * }
     */
    public VerificationToken() {
    }
    public VerificationToken(Long id, String token, Client client, Date expiryDate) {
        this.id = id;
        this.token = token;
        this.client = client;
        this.expiryDate = expiryDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
