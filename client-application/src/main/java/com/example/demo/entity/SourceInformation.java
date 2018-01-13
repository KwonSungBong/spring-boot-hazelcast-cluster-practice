package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ksb on 2018. 1. 9..
 */
@Data
public class SourceInformation implements Serializable {

    public static final int TYPE_ID = 10000;

    private Long id;

    private String sourceOne;
    private String sourceTwo;
    private String sourceThree;
    private String sourceFour;
    private String sourceFive;
    private String sourceSix;
    private String sourceSeven;
    private String sourceEight;
    private String sourceNine;
    private String sourceTen;

    private int sourceInt;
    private boolean sourceBoolean;
    private InformationEnumType sourceEnumType;
    private Date sourceDate;

}
