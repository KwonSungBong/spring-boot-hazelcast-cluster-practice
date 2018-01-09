package com.example.demo.repository;

import com.example.demo.entity.SourceInformation;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Repository
public class CacheRepository {

    private final String QUEUE_KEY = "migration.queue";

    @Autowired
    protected HazelcastInstance hazelcastInstance;

    public List<SourceInformation> poll() {
        IQueue<List<SourceInformation>> queue = hazelcastInstance.getQueue(QUEUE_KEY);
        return queue.poll();
    }

    public void add(List<SourceInformation> sourceInformationList) {
        IQueue<List<SourceInformation>> queue = hazelcastInstance.getQueue(QUEUE_KEY);
        queue.add(sourceInformationList);
    }

    public void clear() {
        IQueue<List<SourceInformation>> queue = hazelcastInstance.getQueue(QUEUE_KEY);
        queue.clear();
    }

    public void forceUnLock() {
        hazelcastInstance.getLock(QUEUE_KEY).forceUnlock();
    }

    public boolean isLocked() {
        return hazelcastInstance.getLock(QUEUE_KEY).isLocked();
    }

}
