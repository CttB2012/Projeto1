package com.brq.projeto1.repositories;


import com.brq.projeto1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Repositório da Classe Produto
 * @author WGomes
 * @since release 1.0
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

}
