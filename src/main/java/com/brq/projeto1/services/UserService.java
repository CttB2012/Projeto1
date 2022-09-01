package com.brq.projeto1.services;

import com.brq.projeto1.entities.User;
import com.brq.projeto1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService  {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
       Optional<User> u =  repository.findById(id);
       return u.get();
    }


}
