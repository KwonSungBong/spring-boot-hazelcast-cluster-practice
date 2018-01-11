package com.example.demo.repository;

import com.example.demo.entity.DestinationInformation;
import com.example.demo.entity.QDestinationInformation;
import com.mysema.commons.lang.CloseableIterator;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by ksb on 2018. 1. 10..
 */
@Repository
public class DestinationRepository {

    private final QDestinationInformation qDestinationInformation = QDestinationInformation.destinationInformation;

    @PersistenceContext
    private EntityManager entityManager;

    public List<DestinationInformation> findAll() {
        List<DestinationInformation> destinationInformationList = new JPAQueryFactory(entityManager)
                .selectFrom(qDestinationInformation).fetch();

        return destinationInformationList;
    }

    public void findAll(Consumer<DestinationInformation> action) {
        CloseableIterator<DestinationInformation> destinationInformationCloseableIterator = new JPAQueryFactory(entityManager)
                .selectFrom(qDestinationInformation).iterate();
        destinationInformationCloseableIterator.forEachRemaining(action);
        destinationInformationCloseableIterator.close();
    }

}
