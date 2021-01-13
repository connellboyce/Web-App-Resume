package com.connellboyce.webportfolio.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Connell Boyce on Jan 13 2021
 */
@Configuration
public class SNSConfiguration {

    @Value("${app.secret.accessKey}")
    private String accessKey;

    @Value("${app.secret.secretKey}")
    private String secretKey;

    @Bean(name = "amazonSNS", destroyMethod = "shutdown")
    public AmazonSNSAsync amazonSNSAsync() {
        return AmazonSNSAsyncClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey)))
            .withRegion(Regions.US_EAST_1)
            .build();
    }
}
