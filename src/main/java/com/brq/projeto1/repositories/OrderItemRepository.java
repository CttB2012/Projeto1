package com.brq.projeto1.repositories;

import com.brq.projeto1.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório da Classe Itens do Pedido
 * @author WGomes
 * @since release 1.0
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
