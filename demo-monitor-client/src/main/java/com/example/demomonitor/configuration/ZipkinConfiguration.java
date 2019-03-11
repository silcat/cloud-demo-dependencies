package com.example.demomonitor.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.SamplerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * zipkin配置
 * @author  ytf
 * @date    2018/10/30
 */
@Slf4j
@Configuration
@ConditionalOnClass(SamplerProperties.class)
@ConditionalOnProperty(value = "spring.zipkin.enabled", matchIfMissing = true)
@EnableConfigurationProperties({SamplerProperties.class})
public class ZipkinConfiguration {

    /**
     * 自定义采样率
     * @param config
     * @return
     */
    @Bean
    public Sampler demoSampler(SamplerProperties config) {
        return new DemoPercentageBasedSampler(config);
    }

}

