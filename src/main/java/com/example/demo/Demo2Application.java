package com.example.demo;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server Url")})
@SpringBootApplication
public class Demo2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }


    @SneakyThrows
    @Override
    public void run(String... args) throws Exception {
    }
}