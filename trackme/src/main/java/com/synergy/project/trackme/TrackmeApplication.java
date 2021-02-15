package com.synergy.project.trackme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
public class TrackmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackmeApplication.class, args);
    }

}
