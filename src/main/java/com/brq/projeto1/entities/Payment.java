package com.brq.projeto1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


/**
 * Classe contendo os parâmetros para a criação dos Estados de pagamentos no banco de dados
 * @author WGomes
 * @since release 1.0
 */
@Entity
@Table(name = "tabela_payment")
public class Payment implements Serializable {
    private static final long serialVersionUID = 1l;

    /**
     * Parâmetros que devem ser utilizados para criacão dos Pagamentos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;
    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;

    public Payment (){
    }

    /**
     * Sobrecarga dos Parâmetros que devem ser utilizados para criação dos Estados de Pagamentos
     * @param id
     * @param moment
     * @param order
     */
    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    /**
     * Método de validação para cada entidade Payment criada na base de dados
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }
    /**
     * Método "interno" do Java que cria um identificador para cada entidade Product criada
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

