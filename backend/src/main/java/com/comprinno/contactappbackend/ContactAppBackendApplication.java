package com.comprinno.contactappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.comprinno.contactappbackend")
@EnableJpaRepositories(basePackages = "com.comprinno.contactappbackend.repository", entityManagerFactoryRef = "entityManagerFactory")
public class ContactAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactAppBackendApplication.class, args);
    }

}
