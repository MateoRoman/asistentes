package com.espe.eventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EventoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventoApplication.class, args);
    }

}
