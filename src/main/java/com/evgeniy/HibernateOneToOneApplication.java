package com.evgeniy;

import com.evgeniy.service.StartUpService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HibernateOneToOneApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HibernateOneToOneApplication.class, args);
        context.getBean(StartUpService.class).startUp();
    }
}
