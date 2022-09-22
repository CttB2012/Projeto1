package com.brq.projeto1.entities;


import com.brq.projeto1.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe contendo os parâmetros para a criação de Pedidos no banco de dados
 * @author WGomes
 * @since release 1.0
 */
@Entity
@Table(name = "tabela_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1l;

    /**
     * Parâmetros que devem ser utilizados para criacão dos Pagamentos no Banco de Dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;
    private Integer orderStatus;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    private Order name;

    public Order() {
    }

    /**
     * Sobrecarga dos Parâmetros que devem ser utilizados para criação dos Pedidos
     * @param id
     * @param moment
     * @param orderStatus
     * @param client
     */
    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;

    }


    /**
     * Getters e Setters dos parâmetros
     * @return
     */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Instant getMoment() {
        return moment;
    }
    public void setMoment(Instant moment) {
        this.moment = moment;
    }
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }
    public User getClient() {
        return client;
    }
    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems () {
        return items;
    }

    /**
     * Método que soma a quantidade de itens do pedido e associa com a classe OrderItem
     * @return
     */
    public BigDecimal getTotal(){
        BigDecimal sum = BigDecimal.ZERO;
        for(OrderItem x : items) {
            sum = sum.add(x.getSubTotal());
        }
        return sum;
    }
    /**
     * Método de validação para cada entidade Order criada na base de dados
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }
    /**
     * Método "interno" do Java que cria um identificador para cada entidade Order criada
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
