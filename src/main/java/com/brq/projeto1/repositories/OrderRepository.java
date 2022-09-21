package com.brq.projeto1.repositories;

import com.brq.projeto1.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repositório da Classe Pedido
 * @author WGomes
 * @since release 1.0
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
