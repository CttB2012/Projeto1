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

/**
 * Classe de serviço que se comunica diretamente com o Repositório Pedidos
 * @author WGomes
 * @since release 1.0
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    /**
     * Método para Retornar todos os Pedidos
     * @return
     */
    public List<Order> findAll(){
        return repository.findAll();
    }

    /**
     * Método para Retornar os Pedidos pela ID
     * @param id
     * @return
     */
    public Order findById(Long id){
        try {
            Optional<Order> u =  repository.findById(id);
            return u.get();
        }catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-09", e.getMessage());
        }
    }

    /**
     * Método para Inserir novo Pedido
     * @param obj
     * @return
     */
    public Order insert(Order obj) {
        try {
            Optional<Order> order = repository.findById(obj.getId());
            if (order.isPresent()) {
                throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-07");
            }
        repository.save(obj);
        return obj;
    }catch (ExceptionApiCadastro e) {
            throw e;
        }catch (Exception e ) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CAD-08");
        }
    }

    /**
     * Método para Excluir Pedido
     * @param id
     */
    @DeleteMapping
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-09", e.getMessage());
        }
    }

    /**
     * Método para Atualizar Pedido
     * @param id
     * @param obj
     * @return
     */
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

    /**
     * Método complementar para Atualizar informações do Pedido
     * @param entity
     * @param obj
     */
    private void updateData(Order entity, Order obj) {
        entity.setOrderStatus(obj.getOrderStatus());
    }

}
