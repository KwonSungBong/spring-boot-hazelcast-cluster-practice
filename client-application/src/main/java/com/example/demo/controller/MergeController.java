package com.example.demo.controller;

import com.example.demo.entity.DestinationInformation;
import com.example.demo.service.MergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ksb on 2018. 1. 11..
 */
@RestController
@RequestMapping("/merge")
public class MergeController {

    @Autowired
    private MergeService mergeService;

    @GetMapping("/save")
    public int save(){
        return mergeService.saveAny();
//        return mergeService.save();
    }

    @GetMapping("/find")
    public List<DestinationInformation> find(){
        return mergeService.find();
    }

}
