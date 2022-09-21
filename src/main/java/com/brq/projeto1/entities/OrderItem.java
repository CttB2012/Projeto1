package com.brq.projeto1.entities;

import com.brq.projeto1.entities.pk.OrderItemPk;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe contendo os parâmetros para a associação do Pedido e Quantidade de Itens pedidos
 * @author WGomes
 * @since release 1.0
 */

@Entity
@Table(name = "orderItem")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPk id = new OrderItemPk();
    private Integer quantity;
    private BigDecimal price;

    public OrderItem(){
    }

    /**
     * Sobrecarga dos Parâmetros para a associação do Pedido e Quantidade de Itens pedidos
     * @param order
     * @param product
     * @param quantity
     * @param price
     */
    public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }
    /**
     * Getters e Setters dos parâmetros
     * @return
     */
    @JsonIgnore
    public Order getOrder (){
        return id.getOrder();
    }
    public void setOrder(Order order){
        id.setOrder(order);
    }

    public Product getProduct (){
        return id.getProduct();
    }
    public void setProduct(Product product){
        id.setProduct(product);
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice(Integer quantity) {
        return price.multiply(new BigDecimal(quantity));
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Método que calcula o subtotal do pedido multiplicando o preço pela quantidade de produtos da compra
     * @return
     */
    public BigDecimal getSubTotal(){
        return price.multiply(new BigDecimal(this.quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
