package com.example.demo.service;

import com.example.demo.entity.SourceInformation;
import com.example.demo.repository.JdbcTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksb on 2018. 1. 9..
 */
@Service
public class MigrationService {

    @Autowired
    private JdbcTemplateRepository jdbcTemplateRepository;

    public List<SourceInformation> findAll1() {
        List<SourceInformation> sourceInformationList = jdbcTemplateRepository.findAll();

        return sourceInformationList;
    }

    public List<SourceInformation> findAll2() {
        List<SourceInformation> sourceInformationList = new ArrayList<>();
        jdbcTemplateRepository.findAll((ResultSet rs) -> {
            SourceInformation sourceInformation = new SourceInformation();
            sourceInformation.setId(rs.getLong("id"));
            sourceInformation.setSourceOne(rs.getString("one"));
            sourceInformation.setSourceTwo(rs.getString("two"));
            sourceInformation.setSourceThree(rs.getString("three"));
            sourceInformation.setSourceFour(rs.getString("four"));
            sourceInformation.setSourceFive(rs.getString("five"));
            sourceInformation.setSourceSix(rs.getString("six"));
            sourceInformation.setSourceSeven(rs.getString("seven"));
            sourceInformation.setSourceEight(rs.getString("eight"));
            sourceInformation.setSourceNine(rs.getString("nine"));
            sourceInformation.setSourceTen(rs.getString("ten"));
            sourceInformationList.add(sourceInformation);
        });

        return sourceInformationList;
    }


}
