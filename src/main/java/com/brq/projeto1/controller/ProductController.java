package com.brq.projeto1.controller;


import com.brq.projeto1.entities.Product;
import com.brq.projeto1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Classe para CONTROLAR as requisições de PRODUTOS da API
 * @author WGomes
 * @since Release 1.0
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    /**
     * Método para RETORNAR TODOS os PRODUTOS
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Método para RETORNAR os produtos pela ID
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product u = service.findById(id);
        return ResponseEntity.ok().body(u);
    }
    /**
     * Método para CRIAR novo produto
     * @return
     */
    @PostMapping
    public ResponseEntity<Product> insert (@Valid @RequestBody Product obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/products")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    /**
     * Método para EXCLUIR um produto pela ID
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    /**
     * Método para ATUALIZAR informações de um produto pela ID
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }




}
