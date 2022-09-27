package com.example.market.repositories;

import com.example.market.models.ClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientProductRepository extends JpaRepository<ClientProduct, Long> {
}
