package demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Data
public class TestData implements Serializable {

    public static final int TYPE_ID = 10000;

    private Long id;
    private String name;
    private String test;

}
