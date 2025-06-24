package com.egov.owneradmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.egov.owneradmin", "com.egov.core"})
public class OwnerAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(OwnerAdminApplication.class, args);
    }
}
