package com.brq.projeto1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe contendo os parâmetros para a criação das Categorias no banco de dados
 * @author WGomes
 * @since release 1.0
 */
@Entity
@Table(name = "tabela_category")
@Validated
public class Category implements Serializable {
    private static final long serialVersionUID = 1l;

    /**
     * Parâmetros que devem ser utilizados para criacão das Categorias no Banco de Dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("categoria")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
    public Category() {
    }

    /**
     * Sobrecarga dos Parâmetros que devem ser utilizados para criação das Categorias
     * @param id
     * @param name
     */
   public Category(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    /**
     * Método de validação para cada entidade Category criada na base de dados
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id;
    }

    /**
     * Método "interno" do Java que cria um identificador para cada entidade Category criada
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
