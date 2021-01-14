package com.connellboyce.webportfolio.controller;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {
    Logger logger = LoggerFactory.getLogger(SNSController.class);

    @Autowired
    private AmazonSNSAsync amazonSNSClient;

    @Value("${aws.sns.arn}")
    String TOPIC_ARN;

    @GetMapping("/subscribe/{email}")
    public String addSubscription(@PathVariable String email) {
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
        amazonSNSClient.subscribe(request);
        return "Subscription pending. Please wait for e-mail confirmation. This might take a few minutes.";
    }

    @GetMapping("/sendEmail")
    public String publishMessage() {
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN,buildEmailBody(),"Notification: Amazon SNS");
        amazonSNSClient.publish(publishRequest);
        return "Notification published.";
    }

    private String buildEmailBody() {
        return "This is the sample email response.";
    }
}
