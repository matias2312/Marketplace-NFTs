package com.example.market.Services;

import com.example.market.models.Client;

public interface UserService {

    boolean sendInvoice(Client user, byte[] bytes);

    void clearCart(Client client);

}
