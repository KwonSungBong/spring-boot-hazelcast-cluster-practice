package com.example.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ksb on 2018. 1. 9..
 */
@Data
@Entity
public class DestinationInformation {

    @Id
    private Long id;

    @Column(nullable = true)
    private String destinationOne;
    @Column(nullable = true)
    private String destinationTwo;
    @Column(nullable = true)
    private String destinationThree;
    @Column(nullable = true)
    private String destinationFour;
    @Column(nullable = true)
    private String destinationFive;
    @Column(nullable = true)
    private String destinationSix;
    @Column(nullable = true)
    private String destinationSeven;
    @Column(nullable = true)
    private String destinationEight;
    @Column(nullable = true)
    private String destinationNine;
    @Column(nullable = true)
    private String destinationTen;

    @Column(nullable = true)
    private Integer destinationInt;
    @Column(nullable = true)
    private Boolean destinationBoolean;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private InformationEnumType destinationEnumType;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date destinationDate;

}
