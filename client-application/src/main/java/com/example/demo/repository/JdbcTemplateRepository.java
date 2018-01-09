package com.example.demo.repository;

import com.example.demo.entity.SourceInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Repository
public class JdbcTemplateRepository {

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;

    private static final String FIND_ALL_SQL = "SELECT id, one, two, three, four, five, six, seven, eight, nine, ten FROM migration";

    public List<SourceInformation> findAll() {
        List<SourceInformation> sourceInformationList = testJdbcTemplate.query(
                FIND_ALL_SQL, (rs, rowNum) -> {
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
                    return sourceInformation;
                });

        return sourceInformationList;
    }

    public void findAll(RowCallbackHandler rowCallbackHandler) {
        testJdbcTemplate.query(FIND_ALL_SQL, rowCallbackHandler);
    }


}
