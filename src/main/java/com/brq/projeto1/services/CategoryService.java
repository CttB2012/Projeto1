package com.brq.projeto1.services;

import com.brq.projeto1.controller.exceptions.ExceptionApiCadastro;
import com.brq.projeto1.entities.Category;
import com.brq.projeto1.repositories.CategoryRepository;
import com.brq.projeto1.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public Category insert (Category obj) {
        try {
            Optional<Category> cat = repository.findById(obj.getId());
            if (cat.isPresent()) {
                throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-10");
            }
            repository.save(obj);
            return obj;
        }catch (DatabaseException e ) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-11", e.getMessage());
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-11", e.getMessage());
        }
    }
    public void delete (Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-12", e.getMessage());
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-11", e.getMessage());
        }
    }
    public Category update (Long id, Category obj) {
        try {
            Category entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-12", e.getMessage());
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CAD-11", e.getMessage());
        }
    }
    private void updateData (Category entity, Category obj) {
        entity.setName(obj.getName());
    }

}
