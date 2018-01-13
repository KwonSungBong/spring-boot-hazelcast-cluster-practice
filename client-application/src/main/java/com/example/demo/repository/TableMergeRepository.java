package com.example.demo.repository;

import com.example.demo.entity.DestinationInformation;
import com.example.demo.entity.SourceInformation;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Repository
public class TableMergeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final int PARTITION_SIZE = 40;

    @Transactional
    public int merge(List<SourceInformation> entities , String tableName, List<String> fieldNames) {
        List<DestinationInformation> mergeEntities =  entities.stream().map(sourceInformation -> {
            DestinationInformation destinationInformation = new DestinationInformation();
            destinationInformation.setId(sourceInformation.getId());
            destinationInformation.setDestinationOne(sourceInformation.getSourceOne());
            destinationInformation.setDestinationTwo(sourceInformation.getSourceTwo());
            destinationInformation.setDestinationThree(sourceInformation.getSourceThree());
            destinationInformation.setDestinationFour(sourceInformation.getSourceFour());
            destinationInformation.setDestinationFive(sourceInformation.getSourceFive());
            destinationInformation.setDestinationSix(sourceInformation.getSourceSix());
            destinationInformation.setDestinationSeven(sourceInformation.getSourceSeven());
            destinationInformation.setDestinationEight(sourceInformation.getSourceEight());
            destinationInformation.setDestinationNine(sourceInformation.getSourceNine());
            destinationInformation.setDestinationTen(sourceInformation.getSourceTen());
            return destinationInformation;
        }).collect(toList());

        return merge(mergeEntities, tableName, fieldNames, false);
    }

// 단위 나눠서 한방에 쿼리 보내도록 수정
    @Transactional
    public int merge(List<DestinationInformation> entities, String tableName, List<String> fieldNames, boolean isThrowable) {
        int executedCnt = 0;
        String query = null;
        for (List<DestinationInformation> entityList : Lists.partition(entities, PARTITION_SIZE)) {
            try {
                for(DestinationInformation destinationInformation : entityList) {
                    query = "INSERT INTO destination_information(id, destination_one, destination_two, destination_three, " +
                            "destination_four, destination_five, destination_six, destination_seven, destination_eight, " +
                            "destination_nine, destination_ten) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                            "ON DUPLICATE KEY UPDATE destination_one=VALUES(?), destination_two=?, destination_three=?, " +
                            "destination_four=?, destination_five=?, destination_six=?, destination_seven=?, " +
                            "destination_eight=?, destination_nine=?, destination_ten=?";
                    executedCnt += entityManager.createNativeQuery(query)
                            .setParameter(1, destinationInformation.getId())
                            .setParameter(2, destinationInformation.getDestinationOne())
                            .setParameter(3, destinationInformation.getDestinationTwo())
                            .setParameter(4, destinationInformation.getDestinationThree())
                            .setParameter(5, destinationInformation.getDestinationFour())
                            .setParameter(6, destinationInformation.getDestinationFive())
                            .setParameter(7, destinationInformation.getDestinationSix())
                            .setParameter(8, destinationInformation.getDestinationSeven())
                            .setParameter(9, destinationInformation.getDestinationEight())
                            .setParameter(10, destinationInformation.getDestinationNine())
                            .setParameter(11, destinationInformation.getDestinationTen())
                            .setParameter(12, destinationInformation.getDestinationOne() + "DUPLICATE")
                            .setParameter(13, destinationInformation.getDestinationTwo() + "DUPLICATE")
                            .setParameter(14, destinationInformation.getDestinationThree() + "DUPLICATE")
                            .setParameter(15, destinationInformation.getDestinationFour() + "DUPLICATE")
                            .setParameter(16, destinationInformation.getDestinationFive() + "DUPLICATE")
                            .setParameter(17, destinationInformation.getDestinationSix() + "DUPLICATE")
                            .setParameter(18, destinationInformation.getDestinationSeven() + "DUPLICATE")
                            .setParameter(19, destinationInformation.getDestinationEight() + "DUPLICATE")
                            .setParameter(20, destinationInformation.getDestinationNine() + "DUPLICATE")
                            .setParameter(21, destinationInformation.getDestinationTen() + "DUPLICATE")
                            .executeUpdate();
                }
            } catch (Exception e) {
                if (isThrowable) {
                    throw new RuntimeException(e);
                } else {
                    log.error("failed to execute many merge-into sql: {}, table: {}", query, tableName, e);
                }
            }
        }
        log.trace("merge data into {}, size: {}", tableName, executedCnt);
        return executedCnt;
    }

//    private String makeSql(List<DestinationInformation> entityList) {
//        StringBuffer sql = new StringBuffer();
//
//        for(DestinationInformation destinationInformation : entityList) {
//            sql.append()"INSERT INTO destination_information(id, destination_one, destination_two, destination_three, " +
//                    "destination_four, destination_five, destination_six, destination_seven, destination_eight, " +
//                    "destination_nine, destination_ten) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
//                    "ON DUPLICATE KEY UPDATE destination_one=?, destination_two=?, destination_three=?, " +
//                    "destination_four=?, destination_five=?, destination_six=?, destination_seven=?, " +
//                    "destination_eight=?, destination_nine=?, destination_ten=?";
//
//        }
//
//        return "";
//    }

//    @Transactional
//    public int merge(List<DestinationInformation> entities, String tableName, List<String> fieldNames, boolean isThrowable) {
//        int executedCnt = 0;
//        String query = null;
//        for (List<DestinationInformation> entityList : Lists.partition(entities, PARTITION_SIZE)) {
//            try {
//                for(DestinationInformation destinationInformation : entityList) {
//                    query = "INSERT INTO destination_information(id, destination_one, destination_two, destination_three, " +
//                            "destination_four, destination_five, destination_six, destination_seven, destination_eight, " +
//                            "destination_nine, destination_ten) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
//                            "ON DUPLICATE KEY UPDATE destination_one=?, destination_two=?, destination_three=?, " +
//                            "destination_four=?, destination_five=?, destination_six=?, destination_seven=?, " +
//                            "destination_eight=?, destination_nine=?, destination_ten=?";
//                    executedCnt += entityManager.createNativeQuery(query)
//                            .setParameter(1, destinationInformation.getId())
//                            .setParameter(2, destinationInformation.getDestinationOne())
//                            .setParameter(3, destinationInformation.getDestinationTwo())
//                            .setParameter(4, destinationInformation.getDestinationThree())
//                            .setParameter(5, destinationInformation.getDestinationFour())
//                            .setParameter(6, destinationInformation.getDestinationFive())
//                            .setParameter(7, destinationInformation.getDestinationSix())
//                            .setParameter(8, destinationInformation.getDestinationSeven())
//                            .setParameter(9, destinationInformation.getDestinationEight())
//                            .setParameter(10, destinationInformation.getDestinationNine())
//                            .setParameter(11, destinationInformation.getDestinationTen())
//                            .setParameter(12, destinationInformation.getDestinationOne() + "DUPLICATE")
//                            .setParameter(13, destinationInformation.getDestinationTwo() + "DUPLICATE")
//                            .setParameter(14, destinationInformation.getDestinationThree() + "DUPLICATE")
//                            .setParameter(15, destinationInformation.getDestinationFour() + "DUPLICATE")
//                            .setParameter(16, destinationInformation.getDestinationFive() + "DUPLICATE")
//                            .setParameter(17, destinationInformation.getDestinationSix() + "DUPLICATE")
//                            .setParameter(18, destinationInformation.getDestinationSeven() + "DUPLICATE")
//                            .setParameter(19, destinationInformation.getDestinationEight() + "DUPLICATE")
//                            .setParameter(20, destinationInformation.getDestinationNine() + "DUPLICATE")
//                            .setParameter(21, destinationInformation.getDestinationTen() + "DUPLICATE")
//                            .executeUpdate();
//                }
//            } catch (Exception e) {
//                if (isThrowable) {
//                    throw new RuntimeException(e);
//                } else {
//                    log.error("failed to execute many merge-into sql: {}, table: {}", query, tableName, e);
//                }
//            }
//        }
//        log.trace("merge data into {}, size: {}", tableName, executedCnt);
//        return executedCnt;
//    }

}
