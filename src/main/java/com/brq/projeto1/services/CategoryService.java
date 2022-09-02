package com.brq.projeto1.services;

import com.brq.projeto1.entities.Category;
import com.brq.projeto1.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
       Optional<Category> u =  repository.findById(id);
       return u.get();
    }


}
