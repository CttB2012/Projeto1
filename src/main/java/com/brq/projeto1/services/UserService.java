package com.brq.projeto1.services;

import com.brq.projeto1.entities.DTO.UserDTO;
import com.brq.projeto1.entities.User;
import com.brq.projeto1.repositories.UserRepository;
import com.brq.projeto1.controller.exceptions.ExceptionApiCadastro;
import com.brq.projeto1.services.exceptions.DatabaseException;
import com.brq.projeto1.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll(){

        List<User> listUserDataBase =  repository.findAll();

        List<UserDTO> listUserDTO = new ArrayList<>();
        for(User obj :listUserDataBase){

            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(obj.getUserId());
            userDTO.setName(obj.getName());
            userDTO.setEmail(obj.getEmail());
            userDTO.setPhone(obj.getPhone());
            listUserDTO.add(userDTO);
        }
        return listUserDTO;
    }

    public UserDTO findById(Long id){
        try {
            Optional<User> obj =  repository.findById(id);
            UserDTO userDTO = new UserDTO();
            User userDataBase = obj.get();
            userDTO.setName(userDataBase.getName());
            userDTO.setEmail(userDataBase.getEmail());
            userDTO.setPhone(userDataBase.getPhone());
            return userDTO;
        }
        catch (Exception e){
            throw new ResourceNotFoundException(id);
        }
    }

    public User insert(User obj) {
        try {
            Optional<User> user = repository.findByEmail(obj.getEmail());
            if (user.isPresent()) {
                throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST,"CAD-01");
            }
            repository.save(obj);
            return obj;
        }catch (DatabaseException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST,"CAD-02", e.getMessage());

        }catch (ExceptionApiCadastro e) {
                throw e;
        }catch (Exception e){
            throw  new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR,"CAD-02",e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-03", e.getMessage());
        }catch (DataIntegrityViolationException e ) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public User update(Long id, User obj) {
        try {
            User entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CAD-03", e.getMessage());
        }catch (Exception e){
            throw  new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR,"CAD-02",e.getMessage());
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
