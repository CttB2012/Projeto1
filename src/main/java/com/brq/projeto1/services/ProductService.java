package com.brq.projeto1.services;


import com.brq.projeto1.controller.exceptions.ExceptionApiCadastro;
import com.brq.projeto1.entities.Product;
import com.brq.projeto1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        try {
            Optional<Product> u = repository.findById(id);
            return u.get();
            }catch (Exception e) {
                throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-04", e.getMessage());
        }
    }
    public Product insert(Product obj) {
        try {
            Optional<Product> product = repository.findById(obj.getId());
            if (product.isPresent()) {
                throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-05");
            }
            repository.save(obj);
            return obj;
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR,"CAD-06", e.getMessage());
        }
    }
    public void  delete(Long id){
        try {
            repository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-04", e.getMessage());
        }
    }
    public  Product update(Long id, Product obj) {
        try {
            Product product = repository.getOne(id);
            updateData(product, obj);
            return repository.save(product);
        }catch (EntityNotFoundException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-04", e.getMessage());
        }
    }
    private void updateData(Product product, Product obj) {
        product.setName(obj.getName());
        product.setPrice(obj.getPrice());
        product.setDescription(obj.getDescription());
        product.setImgUrl(obj.getImgUrl());

    }

}
