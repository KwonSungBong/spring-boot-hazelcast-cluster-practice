package com.example.demo.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by whilemouse on 17. 9. 5.
 */
//@Configuration
public class CacheConfig {

//    @Bean
//    CacheManager cacheManager() {
//        return new HazelcastCacheManager(hazelcastInstance());
//    }
//
//
//    @Bean
//    HazelcastInstance hazelcastInstance() {
//        return HazelcastClient.newHazelcastClient();
//    }

//    hazelcast:
//    urls: localhost:5701

//    @Value("${hazelcast.urls}")
//    private String[] hazelcastUrls;
//
//    @Value("${hazelcast.pool.size ?: 10}")
//    private int poolSize;
//
//    @Bean
//    public HazelcastInstance hazelcastInstance() {
//        ClientConfig config = new ClientConfig();
//        config.setExecutorPoolSize(poolSize);
//        config.getNetworkConfig().addAddress(hazelcastUrls);
//        config.getNetworkConfig().setSmartRouting(false);
//        return HazelcastClient.newHazelcastClient(config);
//    }
//
//    @Bean
//    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
//        return new HazelcastCacheManager(hazelcastInstance);
//    }


//    @Value("${hazelcast.urls}")
//    private String[] hazelcastUrls;
//
//    @Bean
//    public ClientConfig clientConfig() {
//        ClientConfig config = new ClientConfig();
//        config.getNetworkConfig().addAddress(hazelcastUrls);
//        config.getNetworkConfig().setSmartRouting(false);
//        return config;
//    }
//
//    @Bean
//    public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
//        return HazelcastClient.newHazelcastClient(clientConfig);
//    }
}
