package com.brq.projeto1.controller;


import com.brq.projeto1.entities.DTO.UserDTO;
import com.brq.projeto1.entities.User;
import com.brq.projeto1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Classe para CONTROLAR as requisições de USUÁRIOS da API
 * @author WGomes
 * @since Release 1.0
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * Método para LISTAR TODOS os USUÁRIOS
     * @return
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
        
    }
    /**
     * Método para LISTAR os USUÁRIOS pela ID
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO u = service.findById(id);
        return ResponseEntity.ok().body(u);
    }
    /**
     * Método para CRIAR novo USUÁRIO
     * @return
     */
    @PostMapping
    public ResponseEntity<User> insert (@Valid @RequestBody User obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getUserId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    /**
     * Método para EXCLUIR um USUÁRIO pela ID
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    /**
     * Método para ATUALIZAR informações de um USUÁRIO pela ID
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
