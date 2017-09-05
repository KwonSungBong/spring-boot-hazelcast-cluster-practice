package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by whilemouse on 17. 9. 5.
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String email;

    private String name;

    private String password;

}
