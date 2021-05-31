package org.gagneray.api.banditproblemapi;

import org.gagneray.api.banditproblemapi.configuration.TestBedProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({TestBedProperties.class})
public class BanditproblemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanditproblemApiApplication.class, args);
    }

}
