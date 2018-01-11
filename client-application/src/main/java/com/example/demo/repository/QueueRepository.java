package com.example.demo.repository;

import com.example.demo.entity.SourceInformation;
import com.google.common.collect.Lists;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Repository
public class QueueRepository {

    private final String QUEUE_KEY = "migration.queue";

    @Autowired
    private HazelcastInstance hazelcastInstance;

    public List<SourceInformation> dequeue() {
        IQueue<List<SourceInformation>> queue = hazelcastInstance.getQueue(QUEUE_KEY);
        List<SourceInformation> sourceInformationList = queue.poll();
        return sourceInformationList == null ? Lists.newArrayList() : sourceInformationList;
    }

    public void enqueue(List<SourceInformation> sourceInformationList) {
        IQueue<List<SourceInformation>> queue = hazelcastInstance.getQueue(QUEUE_KEY);
        queue.add(sourceInformationList);
    }

    public void clear() {
        IQueue<List<SourceInformation>> queue = hazelcastInstance.getQueue(QUEUE_KEY);
        queue.clear();
    }

}
