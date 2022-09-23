package com.brq.projeto1.controller;


import com.brq.projeto1.entities.Category;
import com.brq.projeto1.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Classe para CONTROLAR as requisições das CATEGORIAS da API
 * @author WGomes
 * @since Release 1.0
 */
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    /**
     * Método para LISTAR TODAS as catedorias
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    /**
     * Método para LISTAR  as catedorias PELA ID
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category u = service.findById(id);
        return ResponseEntity.ok().body(u);
    }

    /**
     * Método para CRIAR uma nova categoria
     * @return
     */
    @PostMapping
    public ResponseEntity<Category> insert (@Valid @RequestBody Category obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getName()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    /**
     * Método para EXCLUIR uma categoria pela ID
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método para ATUALIZAR informações de uma categoria pela ID
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


}
