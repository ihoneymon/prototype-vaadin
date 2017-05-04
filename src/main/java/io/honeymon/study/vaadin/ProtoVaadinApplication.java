package io.honeymon.study.vaadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProtoVaadinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProtoVaadinApplication.class, args);
    }
}
