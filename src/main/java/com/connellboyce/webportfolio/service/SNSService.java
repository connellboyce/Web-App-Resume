package com.connellboyce.webportfolio.service;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SNSService implements PubSubService {
    Logger logger = LoggerFactory.getLogger(SNSService.class);

    @Autowired
    private AmazonSNSAsync amazonSNSClient;

    @Value("${aws.sns.arn}")
    String TOPIC_ARN;

    @Override
    public String subscribe(String email) {
        logger.info("Attempting to subscribe to email={}",email);
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
        amazonSNSClient.subscribe(request);
        return "Subscription pending. Please wait for e-mail confirmation. This might take a few minutes.";
    }

    @Override
    public String publish() {
        logger.info("Attempting to publish message.");
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN,"test message","Notification: Amazon SNS");
        amazonSNSClient.publish(publishRequest);
        return "Notification published.";
    }
}
