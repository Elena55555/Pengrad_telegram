package com.sky.pengrad_telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PengradTelegramApplication  {

    public static void main(String[] args) {
        SpringApplication.run(PengradTelegramApplication2.class, args);
    }

}