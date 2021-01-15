package com.connellboyce.webportfolio.controller;

import com.connellboyce.webportfolio.payload.request.EmailRequest;
import com.connellboyce.webportfolio.service.PubSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SNSController {

    @Autowired
    PubSubService pubSubService;

    @GetMapping("/sns/subscribe/{email}")
    public String addSubscription(@PathVariable String email) {
        return pubSubService.subscribe(email);
    }

    @PostMapping("/sns/send")
    public ResponseEntity<?> publishMessage(@Valid @RequestBody EmailRequest emailRequest) { return pubSubService.publish(emailRequest); }
}
