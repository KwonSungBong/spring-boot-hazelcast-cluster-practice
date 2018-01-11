package com.example.demo.service;

import com.example.demo.entity.SourceInformation;
import com.example.demo.repository.AnyTableMergeRepository;
import com.example.demo.repository.JdbcTemplateRepository;
import com.example.demo.repository.QueueRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ksb on 2018. 1. 9..
 */
@Slf4j
@Service
public class MigrationService {

    private final static int PARTITION_SIZE = 10;

    @Autowired
    private JdbcTemplateRepository jdbcTemplateRepository;

    @Autowired
    private AnyTableMergeRepository anyTableMergeRepository;

    @Autowired
    private QueueRepository queueRepository;

    public void migration() {
        boolean reset = true;
        load(reset);
    }

    public void poll() {
        merge();
    }

    private void load(boolean reset) {
        if (reset) queueRepository.clear();

        List<SourceInformation> sourceInformationList = Lists.newArrayList();
        jdbcTemplateRepository.findAll(rs -> {
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

            if (sourceInformationList.size() == PARTITION_SIZE) {
                queueRepository.enqueue(Lists.newArrayList(sourceInformationList));
                sourceInformationList.clear();
            }
        });

        if (!sourceInformationList.isEmpty()) {
            queueRepository.enqueue(Lists.newArrayList(sourceInformationList));
            sourceInformationList.clear();
        }
    }

    private void merge() {
        List<SourceInformation> sourceInformationList = queueRepository.dequeue();

        if (sourceInformationList.size() == 0) return;

        int updateSize = 0;
        try {
            updateSize = anyTableMergeRepository.merge(sourceInformationList, "DESTINATION_INFORMATION",
                    Arrays.asList("id", "one", "two", "three", "four",
                            "five", "six", "seven", "eight", "nine", "ten"));
        } catch (Exception e) {
            if (sourceInformationList.size() < 5) {
                for (SourceInformation sourceInformation : sourceInformationList) {
                    queueRepository.enqueue(Lists.newArrayList(sourceInformation));
                }
            } else {
                Lists.partition(sourceInformationList, sourceInformationList.size() / 2).forEach(partition -> {
                    queueRepository.enqueue(Lists.newArrayList(partition));
                });
            }
            throw e;
        }

        log.info("update information. size: {}", updateSize);
    }

}
