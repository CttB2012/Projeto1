package com.brq.projeto1.resources;


import com.brq.projeto1.entities.DTO.UserDTO;
import com.brq.projeto1.entities.User;
import com.brq.projeto1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO u = service.findById(id);
        return ResponseEntity.ok().body(u);
    }

    @PostMapping
    public ResponseEntity<User> insert (@Valid @RequestBody User obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getUserId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

//    @PostMapping(path = "/users")
//    public ResponseEntity<User> insertTest (@Valid @RequestBody User obj) {
//        System.out.println(obj);
//        obj = service.insert(obj);
//       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(obj.getUserId()).toUri();
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
