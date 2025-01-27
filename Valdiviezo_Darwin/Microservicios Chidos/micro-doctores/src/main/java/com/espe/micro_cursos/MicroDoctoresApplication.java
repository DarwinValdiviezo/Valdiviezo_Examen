package com.espe.micro_cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroDoctoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroDoctoresApplication.class, args);
    }

}
