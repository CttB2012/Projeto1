package com.brq.projeto1.repositories;

import com.brq.projeto1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByEmail (String email);

}
