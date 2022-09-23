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

/**
 * Classe de serviço que se comunica diretamente com o Repositório Categoria
 * @author WGomes
 * @since release 1.0
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    /**
     * Método para Retornar todos as Categorias
     * @return
     */
    public List<Category> findAll(){
        return repository.findAll();
    }

    /**
     * Método para Retornar as Categorias pela ID
     * @param id
     * @return
     */
    public Category findById(Long id){
        try {
            Optional<Category> u =  repository.findById(id);
            return u.get();
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-12", e.getMessage());
        }
    }

    /**
     * Método para Inserir nova Categoria
     * @param obj
     * @return
     */
    public Category insert (Category obj) {
        try {
            Optional<Category> cat = repository.findByName(obj.getName());
            if (cat.isPresent()) {
                throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-10");
            }
            repository.save(obj);
            return obj;
        }catch (ExceptionApiCadastro e) {
            throw e;

        }catch (DatabaseException e ) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-11", e.getMessage());
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-11", e.getMessage());
        }
    }

    /**
     *  Método para Excluir Categoria
     * @param id
     */
    public void delete (Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-12", e.getMessage());
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-11", e.getMessage());
        }
    }

    /**
     * Método para Atualizar Categoria
     * @param id
     * @param obj
     * @return
     */
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

    /**
     * Método complementar para Atualizar informações da Categoria
     * @param entity
     * @param obj
     */
    private void updateData (Category entity, Category obj) {
        entity.setName(obj.getName());
    }

}
