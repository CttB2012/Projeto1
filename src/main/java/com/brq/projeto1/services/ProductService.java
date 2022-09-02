package com.brq.projeto1.services;


import com.brq.projeto1.entities.Product;
import com.brq.projeto1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
       Optional<Product> u =  repository.findById(id);
       return u.get();
    }


}
