package com.icommerce.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistrationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistrationServiceApplication.class, args);
    }
}
