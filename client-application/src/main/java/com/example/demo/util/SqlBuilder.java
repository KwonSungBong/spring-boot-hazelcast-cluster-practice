package com.example.demo.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Primitives;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ksb on 2018. 1. 13..
 */
public class SqlBuilder {

    private static final Converter<String, String> DB_COLUMN_NAME_CONVERTER = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

    private static final int SOURCE_KEY = 0;
    private static final int DESTINATION_KEY = 1;

    private static final String PIPE = "|";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("yyyy/MM/dd HH:MM:ss");

    public static String buildMergeSql(List<? extends Object> entities, String tableName, List<String[]> columnList) throws NoSuchFieldException, IllegalAccessException {
        Class entityClass = entities.get(0).getClass();
        List<String> fieldNames = Lists.newArrayList();
        Map<String, Field> targetFieldType = Maps.newHashMap();

        for(String[] columnArray : columnList) {
            final String destinationColumnName = DB_COLUMN_NAME_CONVERTER.convert(columnArray[DESTINATION_KEY]);
            final Field sourceField = entityClass.getDeclaredField(columnArray[SOURCE_KEY]);

            targetFieldType.put(destinationColumnName, sourceField);
            fieldNames.add(destinationColumnName);
        }

        List<StringBuilder> fieldValues = Lists.newArrayList();
        for (int i = 0; i < entities.size(); i++) {
            final StringBuilder builder = new StringBuilder();
            builder.append("(");
            final Object entity = entities.get(i);
            for(int j = 0; j < fieldNames.size(); j++) {
                String fieldName = fieldNames.get(j);
                Field field = targetFieldType.get(fieldName);
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                if (Date.class.isAssignableFrom(field.getType())) {
                    Object fieldValue = null;
                    fieldValue = field.get(entity);
                    String value = fieldValue != null ? DATE_FORMATTER.print(Date.class.cast(fieldValue).getTime()) : "null";
                    builder.append("'");
                    builder.append(value);
                    builder.append("'");
                } else if (String.class.isAssignableFrom(field.getType())) {
                    String value = String.valueOf(field.get(entity));
                    builder.append("'");
                    builder.append(value);
                    builder.append("'");
                } else if ((fieldType.isPrimitive() && Boolean.class.isAssignableFrom(Primitives.wrap(fieldType)))) {
                    Object fieldValue = field.get(entity);
                    builder.append(Boolean.class.cast(fieldValue).booleanValue() ? "1" : "0");
                } else {
                    String value;
                    if (Enum.class.isAssignableFrom(field.getType())) {
                        value = Enum.class.cast(field.get(entity)).name();
                        builder.append("'");
                        builder.append(value);
                        builder.append("'");
                    } else {
                        value = String.valueOf(field.get(entity));
                        builder.append(value);
                    }
                }
                if(fieldNames.size() - 1 > j) builder.append(",");
            }
            builder.append(")");
            fieldValues.add(builder);
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append("(");
        sql.append(String.join(",", fieldNames));
        sql.append(") VALUES ");
        sql.append(String.join(",", fieldValues));
        sql.append(" ON DUPLICATE KEY UPDATE ");
        sql.append(fieldNames.stream()
                .map(fieldName -> String.format("%s=VALUES(%s)", fieldName, fieldName))
                .collect(Collectors.joining(",")));

        return sql.toString();
    }

}

