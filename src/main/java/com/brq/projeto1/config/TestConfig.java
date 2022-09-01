package com.brq.projeto1.config;


import com.brq.projeto1.entities.User;
import com.brq.projeto1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null,"Maria Brown", "mariab@gmail.com", "999888999", "123456");
        User u2 = new User(null,"Alex Green", "alexg@gmail.com", "777888999", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
