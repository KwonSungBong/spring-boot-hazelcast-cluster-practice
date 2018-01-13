package com.example.demo.repository;

import com.example.demo.util.SqlBuilder;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class AnyTableMergeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final int PARTITION_SIZE = 40;

    @Transactional
    public int merge(List<? extends Object> entities, String tableName, List<String[]> columnList) {
        return merge(entities, tableName, columnList, false);
    }

    @Transactional
    public int merge(List<? extends Object> entities, String tableName, List<String[]> columnList, boolean isThrowable) {
        int executedCnt = 0;
        String query = null;
        for (List<?> entityList : Lists.partition(entities, PARTITION_SIZE)) {
            try {
                query = SqlBuilder.buildMergeSql(entityList, tableName, columnList);
                executedCnt += entityManager.createNativeQuery(query)
                        .executeUpdate();
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

}
