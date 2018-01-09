package com.example.demo.controller;

import com.example.demo.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by whilemouse on 17. 9. 5.
 */
@RestController
@RequestMapping("/migration")
public class MigrationController {

    @Autowired
    private MigrationService migrationService;

    @GetMapping
    public void migration(){

    }

}
