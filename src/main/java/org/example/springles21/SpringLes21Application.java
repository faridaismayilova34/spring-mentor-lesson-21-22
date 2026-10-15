package org.example.springles21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringLes21Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringLes21Application.class, args);
    }

}
