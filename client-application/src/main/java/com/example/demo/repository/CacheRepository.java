package com.example.demo.repository;

import com.example.demo.entity.DestinationInformation;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by ksb on 2018. 1. 10..
 */
@Repository
public class CacheRepository {

    private final String CACHE_KEY = "migration.cache";

    @Autowired
    private HazelcastInstance hazelcastInstance;

    public DestinationInformation get(long id) {
        IMap<Long, DestinationInformation> cacheMap = hazelcastInstance.getMap(CACHE_KEY);
        return cacheMap.get(id);
    }

    public List<DestinationInformation> get(List<Long> idList) {
        Set<Long> idSet = Sets.newHashSet(idList);
        IMap<Long, DestinationInformation> cacheMap = hazelcastInstance.getMap(CACHE_KEY);
        Map<Long, DestinationInformation> destinationInformationMap = cacheMap.getAll(idSet);
        return Lists.newArrayList(destinationInformationMap.values());
    }

    public List<DestinationInformation> get() {
        IMap<Long, DestinationInformation> cacheMap = hazelcastInstance.getMap(CACHE_KEY);
        return Lists.newArrayList(cacheMap.values());
    }

    public void put(DestinationInformation destinationInformation) {
        IMap<Long, DestinationInformation> cacheMap = hazelcastInstance.getMap(CACHE_KEY);
        cacheMap.put(destinationInformation.getId(), destinationInformation);
    }

    public void put(List<DestinationInformation> destinationInformationList) {
        IMap<Long, DestinationInformation> cacheMap = hazelcastInstance.getMap(CACHE_KEY);
        Map<Long, DestinationInformation> destinationInformationMap = destinationInformationList.stream()
                .collect(Collectors.toMap(DestinationInformation::getId, Function.identity()));
        cacheMap.putAll(destinationInformationMap);
    }

    public void destory() {
        IMap<Long, DestinationInformation> cacheMap = hazelcastInstance.getMap(CACHE_KEY);
        cacheMap.destroy();
    }

}
