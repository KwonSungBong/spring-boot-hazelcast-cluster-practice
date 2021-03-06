package com.example.demo.service;

import com.example.demo.entity.DestinationInformation;
import com.example.demo.entity.InformationEnumType;
import com.example.demo.entity.SourceInformation;
import com.example.demo.repository.AnyTableMergeRepository;
import com.example.demo.repository.DestinationRepository;
import com.example.demo.repository.TableMergeRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ksb on 2018. 1. 11..
 */
@Slf4j
@Service
public class MergeService {

    @Autowired
    private AnyTableMergeRepository anyTableMergeRepository;

    @Autowired
    private TableMergeRepository tableMergeRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Transactional
    public int saveAny() {
        SourceInformation sourceInformation1 = new SourceInformation();
        sourceInformation1.setId(1L);
        sourceInformation1.setSourceOne("SourceOne1");
        sourceInformation1.setSourceTwo("SourceTwo1");
        sourceInformation1.setSourceThree("SourceThree1");
        sourceInformation1.setSourceFour("SourceFour1");
        sourceInformation1.setSourceFive("SourceFive1");
        sourceInformation1.setSourceSix("SourceSix1");
        sourceInformation1.setSourceSeven("SourceSeven1");
        sourceInformation1.setSourceEight("SourceEight1");
        sourceInformation1.setSourceNine("SourceNine1");
        sourceInformation1.setSourceTen("SourceTen1");
        sourceInformation1.setSourceInt(1);
        sourceInformation1.setSourceBoolean(true);
        sourceInformation1.setSourceEnumType(InformationEnumType.enumType3);
        sourceInformation1.setSourceDate(DateTime.now().toDate());
        SourceInformation sourceInformation2 = new SourceInformation();
        sourceInformation2.setId(2L);
        sourceInformation2.setSourceOne("SourceOne2");
        sourceInformation2.setSourceTwo("SourceTwo2");
        sourceInformation2.setSourceThree("SourceThree2");
        sourceInformation2.setSourceFour("SourceFour2");
        sourceInformation2.setSourceFive("SourceFive2");
        sourceInformation2.setSourceSix("SourceSix2");
        sourceInformation2.setSourceSeven("SourceSeven2");
        sourceInformation2.setSourceEight("SourceEight2");
        sourceInformation2.setSourceNine("SourceNine2");
        sourceInformation2.setSourceTen("SourceTen2");
        sourceInformation2.setSourceInt(2);
        sourceInformation2.setSourceBoolean(true);
        sourceInformation2.setSourceEnumType(InformationEnumType.enumType2);
        sourceInformation2.setSourceDate(DateTime.now().toDate());
        SourceInformation sourceInformation3 = new SourceInformation();
        sourceInformation3.setId(3L);
        sourceInformation3.setSourceOne("SourceOne3");
        sourceInformation3.setSourceTwo("SourceTwo3");
        sourceInformation3.setSourceThree("SourceThree3");
        sourceInformation3.setSourceFour("SourceFour3");
        sourceInformation3.setSourceFive("SourceFive3");
        sourceInformation3.setSourceSix("SourceSix3");
        sourceInformation3.setSourceSeven("SourceSeven3");
        sourceInformation3.setSourceEight("SourceEight3");
        sourceInformation3.setSourceNine("SourceNine3");
        sourceInformation3.setSourceTen("SourceTen3");
        sourceInformation3.setSourceInt(3);
        sourceInformation3.setSourceBoolean(true);
        sourceInformation3.setSourceEnumType(InformationEnumType.enumType1);
        sourceInformation3.setSourceDate(DateTime.now().toDate());
        List<SourceInformation> sourceInformationList = Lists.newArrayList(sourceInformation1, sourceInformation2, sourceInformation3);

        List<String[]> columnList = Lists.newArrayList(
                new String[]{"id", "id"}, new String[]{"sourceOne", "destinationOne"},
                new String[]{"sourceTwo", "destinationTwo"}, new String[]{"sourceThree", "destinationThree"},
                new String[]{"sourceFour", "destinationFour"}, new String[]{"sourceFive", "destinationFive"},
                new String[]{"sourceSix", "destinationSix"}, new String[]{"sourceSeven", "destinationSeven"},
                new String[]{"sourceEight", "destinationEight"}, new String[]{"sourceNine", "destinationNine"},
                new String[]{"sourceTen", "destinationTen"}, new String[]{"sourceInt", "destinationInt"},
                new String[]{"sourceBoolean", "destinationBoolean"}, new String[]{"sourceDate", "destinationDate"},
                new String[]{"sourceEnumType", "destinationEnumType"}
        );

//        String tableName = "DESTINATION_INFORMATION";
        String tableName = "destination_information";
        int updateSize = anyTableMergeRepository.merge(sourceInformationList, tableName, columnList);

        log.info("update  size: {}", updateSize);

        return updateSize;
    }

    public int save() {
        SourceInformation sourceInformation1 = new SourceInformation();
        sourceInformation1.setId(1L);
        sourceInformation1.setSourceOne("SourceOne1");
        sourceInformation1.setSourceTwo("SourceTwo1");
        sourceInformation1.setSourceThree("SourceThree1");
        sourceInformation1.setSourceFour("SourceFour1");
        sourceInformation1.setSourceFive("SourceFive1");
        sourceInformation1.setSourceSix("SourceSix1");
        sourceInformation1.setSourceSeven("SourceSeven1");
        sourceInformation1.setSourceEight("SourceEight1");
        sourceInformation1.setSourceNine("SourceNine1");
        sourceInformation1.setSourceTen("SourceTen1");
        SourceInformation sourceInformation2 = new SourceInformation();
        sourceInformation2.setId(2L);
        sourceInformation2.setSourceOne("SourceOne2");
        sourceInformation2.setSourceTwo("SourceTwo2");
        sourceInformation2.setSourceThree("SourceThree2");
        sourceInformation2.setSourceFour("SourceFour2");
        sourceInformation2.setSourceFive("SourceFive2");
        sourceInformation2.setSourceSix("SourceSix2");
        sourceInformation2.setSourceSeven("SourceSeven2");
        sourceInformation2.setSourceEight("SourceEight2");
        sourceInformation2.setSourceNine("SourceNine2");
        sourceInformation2.setSourceTen("SourceTen2");
        SourceInformation sourceInformation3 = new SourceInformation();
        sourceInformation3.setId(3L);
        sourceInformation3.setSourceOne("SourceOne3");
        sourceInformation3.setSourceTwo("SourceTwo3");
        sourceInformation3.setSourceThree("SourceThree3");
        sourceInformation3.setSourceFour("SourceFour3");
        sourceInformation3.setSourceFive("SourceFive3");
        sourceInformation3.setSourceSix("SourceSix3");
        sourceInformation3.setSourceSeven("SourceSeven3");
        sourceInformation3.setSourceEight("SourceEight3");
        sourceInformation3.setSourceNine("SourceNine3");
        sourceInformation3.setSourceTen("SourceTen3");
        List<SourceInformation> sourceInformationList = Lists.newArrayList(sourceInformation1, sourceInformation2, sourceInformation3);

        int updateSize = tableMergeRepository.merge(sourceInformationList, "DESTINATION_INFORMATION",
                Arrays.asList("id", "one", "two", "three", "four",
                        "five", "six", "seven", "eight", "nine", "ten"));

        log.info("update  size: {}", updateSize);

        return updateSize;
    }

    public List<DestinationInformation> find() {
        return destinationRepository.findAll();
    }

}
