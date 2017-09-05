package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by whilemouse on 17. 9. 5.
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(UserRepository userRepository) {
        return args -> {
            User user = new User();
            user.setEmail("user@user.com");
            user.setName("user");
            user.setPassword("password");
            userRepository.save(user);
        };
    }

}
