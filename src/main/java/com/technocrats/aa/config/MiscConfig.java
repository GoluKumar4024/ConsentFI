package com.technocrats.aa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class MiscConfig {

    @Autowired
    public void configureTimeZone(ObjectMapper objectMapper) {
        objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}
