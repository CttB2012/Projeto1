package com.brq.projeto1.services;

import com.brq.projeto1.controller.exceptions.ExceptionApiCadastro;
import com.brq.projeto1.entities.Order;
import com.brq.projeto1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
       Optional<Order> u =  repository.findById(id);
       return u.get();
    }

    public Order insert(Order obj) {
        try {
            Optional<Order> order = repository.findById(obj.getId());
            if (order.isPresent()) {
                throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-07");
            }
        repository.save(obj);
        return obj;
    }catch (Exception e ) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CAD-08");
        }
    }
    @DeleteMapping
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-09", e.getMessage());
        }
    }
    public Order update (Long id, Order obj) {
        try {
            Order entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-09", e.getMessage());
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CAD-08", e.getMessage());
        }
    }
    private void updateData(Order entity, Order obj) {
        entity.setOrderStatus(obj.getOrderStatus());
    }

}
