package com.connellboyce.webportfolio.service;

import com.connellboyce.webportfolio.payload.request.EmailRequest;
import org.springframework.http.ResponseEntity;

public interface PubSubService {
    public String subscribe(String email);
    public ResponseEntity<?> publish(EmailRequest emailRequest);
}
