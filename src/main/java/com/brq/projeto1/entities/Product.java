package com.brq.projeto1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe contendo os parâmetros para a criação de Produto na banco de dados
 * @author WGomes
 * @since release 1.0
 */
@Entity
@Table(name = "tabela_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1l;

    /**
     * Parâmetros que devem ser utilizados para criacão do Produto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;


    @NotNull(message= "O nome do produto é obrigatório")
    @NotBlank(message = "O nome deve ser informado")
    @NotEmpty(message = "O nome não pode ser vazio")
    @JsonProperty("nome")
    private String name;
    @NotNull(message= "A descrição do produto é obrigatória")
    @NotBlank(message= "A descrição do produto deve ser informada")
    @NotEmpty(message= "A descrição não pode ser vazia")
    @JsonProperty("descrição")
    private String description;

    @NotNull(message= "O preço do produto deve ser informado")
    @JsonProperty("preço")
    private BigDecimal price;
    @JsonProperty("imagem")
    private String imgUrl;

    /**
     * Indicação da Cardinalidade "Muitos para Muitos" entre a tabela Produto e a tabela Categoria
     */
    @ManyToMany
    @JoinTable(name = "tabela_Prod_Categ", joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    /**
     * Indicação da Cardinalidade "Um para Muitos" entre a tabela Produto e a ID do respectivo produto
     */
     public Product (){
    }

    /**
     * Sobrecarga dos Parâmetros que devem ser utilizados para criação de novo Produto
     * @param id
     * @param name
     * @param description
     * @param price
     * @param imgUrl
     */
    public Product(Long id, String name, String description, BigDecimal price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }



    /**
     * Método de validação para cada entidade Product criada na base de dados
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
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
