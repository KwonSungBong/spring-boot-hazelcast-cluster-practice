package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by whilemouse on 17. 9. 5.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

}
