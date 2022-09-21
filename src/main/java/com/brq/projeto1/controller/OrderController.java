package com.brq.projeto1.controller;


import com.brq.projeto1.entities.Order;
import com.brq.projeto1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
* Classe para CONTROLAR as requisições dos PEDIDOS da API
* @author WGomes
* @since Release 1.0
*/

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    /**
     * Método para RETORNAR TODOS os pedidos
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Método para RETORNAR os pedidos pela ID
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById (@PathVariable Long id) {
        Order u = service.findById(id);
        return ResponseEntity.ok().body(u);
    }
    /**
     * Método para CRIAR novo pedido
     * @return
     */
    @PostMapping
    public ResponseEntity<Order> insert (@RequestBody Order obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    /**
     * Método para EXCLUIR um pedido pela ID
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    /**
     * Método para ATUALIZAR informações de um pedido pela ID
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update (@PathVariable Long id, @RequestBody Order obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }



}
