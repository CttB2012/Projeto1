package com.brq.projeto1.repositories;

import com.brq.projeto1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
