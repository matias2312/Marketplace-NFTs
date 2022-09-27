package com.example.market.Services;

import com.example.market.models.ClientProduct;

public interface ClientProductService {
    public ClientProduct getClientProductById(Long Id);
    public void deleteItem(ClientProduct clientProduct);
    public void saveItem(ClientProduct clientProduct);
}
