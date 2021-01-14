package com.connellboyce.webportfolio.controller;

import com.connellboyce.webportfolio.service.PubSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {

    @Autowired
    PubSubService pubSubService;

    @GetMapping("/subscribe/{email}")
    public String addSubscription(@PathVariable String email) {
        return pubSubService.subscribe(email);
    }

    @GetMapping("/sendEmail")
    public String publishMessage() {
        return pubSubService.publish();
    }
}
