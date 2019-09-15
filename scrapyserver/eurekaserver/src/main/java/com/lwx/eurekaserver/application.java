package com.lwx.eurekaserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(application.class).web(true).run(args);
    }
}

