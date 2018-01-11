package com.example.demo.repository;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
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
    public int merge(List<? extends Object> entities, String tableName, List<String> fieldNames) {
        return merge(entities, tableName, fieldNames, false);
    }

    @Transactional
    public int merge(List<? extends Object> entities, String tableName, List<String> fieldNames, boolean isThrowable) {
        int executedCnt = 0;
        String query = null;
        for (List<?> entityList : Lists.partition(entities, PARTITION_SIZE)) {
            try {
                //query = MergeIntoSql.build(entityList, tableName, fieldNames);
                query = buildSql(entityList, tableName, fieldNames);
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

    private String buildSql(List<? extends Object> entities, String tableName, List<String> fieldNames) {
//        "insert into destination_information(destination_one, destination_two, destination_three, " +
//                "destination_four, destination_five, destination_six, destination_seven, destination_eight, " +
//                "destination_nine, destination_ten) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        List<String> fields = Lists.newArrayList();
        for(String fieldName : fieldNames) {
            final String columnName = DB_COLUMN_NAME_CONVERTER.convert(fieldName);
            fields.add(columnName);
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT_INTO (");
        for(int i=0; i<fields.size(); i++) {
            sql.append(fields.get(i));
            if(i == fields.size()) {
                sql.append(") ");
            } else {
                sql.append(", ");
            }
        }
        sql.append("VALUES (");



        return "";
    }

    private static final Converter<String, String> DB_COLUMN_NAME_CONVERTER = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

}
