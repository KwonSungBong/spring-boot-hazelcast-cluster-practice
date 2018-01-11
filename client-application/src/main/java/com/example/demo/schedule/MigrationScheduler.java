package com.example.demo.schedule;

import com.example.demo.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ksb on 2018. 1. 9..
 */
@Component
public class MigrationScheduler {

    @Autowired
    private MigrationService migrationService;

//    @Scheduled(initialDelay = 10 * 1000, fixedDelay = 1 * 1000)
    public void pollCmsData() throws InterruptedException {
        migrationService.poll();
    }

}
