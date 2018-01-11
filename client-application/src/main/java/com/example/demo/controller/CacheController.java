package com.example.demo.controller;

import com.example.demo.entity.DestinationInformation;
import com.example.demo.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by whilemouse on 17. 9. 5.
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/refresh")
    public void refreshCache(){
        cacheService.refreshCache();
    }

    @GetMapping("/all")
    public List<DestinationInformation> getAll(){
        return cacheService.getAll();
    }

}
