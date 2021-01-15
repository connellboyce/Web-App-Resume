package com.connellboyce.webportfolio.service;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.connellboyce.webportfolio.payload.request.EmailRequest;
import com.connellboyce.webportfolio.payload.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> publish(EmailRequest emailRequest) {
        logger.info("Attempting to publish message.");
        String emailSubject = emailRequest.getEmailSubject();
        String returnEmail = emailRequest.getReturnEmail();
        String content = emailRequest.getContent();
        String message = "Message From: " + returnEmail + "\n" + content;
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN,message,emailSubject);
        amazonSNSClient.publish(publishRequest);

        if ("".equals(content) ||  "".equals(returnEmail)) {
            logger.error("Email is missing body or return address.");
            return ResponseEntity.badRequest().build();
        }

        logger.info("Successfully sent email");
        return ResponseEntity.ok(new MessageResponse("Email successfully sent!"));
    }
}
