package demo.config;

import demo.dto.TestData;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by whilemouse on 17. 9. 5.
 */
@Configuration
public class CacheConfig {

    @Value("${hazelcast.urls}")
    private String[] hazelcastUrls;

    @Value("${hazelcast.pool.size ?: 10}")
    private int poolSize;

    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress(hazelcastUrls).setSmartRouting(false);
        config.setExecutorPoolSize(poolSize);
        config.getSerializationConfig()
                .getSerializerConfigs()
                .addAll(
                        Arrays.asList(
                                new JsonSerializerConfig(TestData.class, TestData.TYPE_ID)
                        ));

        return HazelcastClient.newHazelcastClient(config);
    }

    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }

}
