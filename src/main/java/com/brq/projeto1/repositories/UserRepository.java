package com.brq.projeto1.repositories;

import com.brq.projeto1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Repositório da Classe Usuário
 * @author WGomes
 * @since release 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByEmail (String email);

}
