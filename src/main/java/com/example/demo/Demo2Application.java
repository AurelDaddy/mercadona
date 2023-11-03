package com.example.demo;

import com.example.demo.repository.ProduitRepo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.repository.CategorieRepo;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@SpringBootApplication
public class Demo2Application implements CommandLineRunner {


    CategorieRepo categorieRepo;
    ProduitRepo produitRepo;

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }


    @SneakyThrows
    @Override
    public void run(String... args) throws Exception {
    }
}