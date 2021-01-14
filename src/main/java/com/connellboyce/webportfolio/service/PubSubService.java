package com.connellboyce.webportfolio.service;

public interface PubSubService {
    public String subscribe(String email);
    public String publish();
}
