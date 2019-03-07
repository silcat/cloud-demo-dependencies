package com.example.democore.configuration.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@EnableConfigurationProperties(ManagementServerProperties.class)
public class DemoCoreAutoConfiguration {
    @Autowired
    private ManagementServerProperties managementServerProperties;

    /**
     * mvc相关配置
     * @param managementServerProperties
     * @return
     */
    @Bean
    public WebMvcConfigureration webMvcConfigureration(ManagementServerProperties managementServerProperties){
        return new WebMvcConfigureration(managementServerProperties);
    }

    /**
     * mvc补充配置
     * @param managementServerProperties
     * @return
     */
    @Bean
    public CustomerWebMvcSupportConfiguration customerWebMvcSupportConfiguration(ManagementServerProperties managementServerProperties){
        return new CustomerWebMvcSupportConfiguration(managementServerProperties);
    }

}
