package com.example.demo.service;

import com.example.demo.entity.DestinationInformation;
import com.example.demo.repository.CacheRepository;
import com.example.demo.repository.DestinationRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CacheService {

    private final static int PARTITION_SIZE = 10;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private CacheRepository cacheRepository;

    public List<DestinationInformation> getAll() {
        List<DestinationInformation> destinationInformationList = cacheRepository.get();
        return destinationInformationList;
    }

    public void refreshCache() {
        boolean reset = true;
        refreshCache(reset);
    }

    private void refreshCache(boolean reset) {
        if(reset) cacheRepository.destory();

        List<DestinationInformation> destinationInformationList = Lists.newArrayList();
        destinationRepository.findAll(destinationInformation -> {
            destinationInformationList.add(destinationInformation);
            if(destinationInformationList.size() == PARTITION_SIZE) {
                cacheRepository.put(Lists.newArrayList(destinationInformationList));
                destinationInformationList.clear();
            }
        });
    }

}
