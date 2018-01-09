package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ksb on 2018. 1. 9..
 */
@Data
@Entity
public class DestinationInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinationOne;
    private String destinationTwo;
    private String destinationThree;
    private String destinationFour;
    private String destinationFive;
    private String destinationSix;
    private String destinationSeven;
    private String destinationEight;
    private String destinationNine;
    private String destinationTen;

}
