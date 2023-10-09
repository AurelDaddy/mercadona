package com.example.demo;

import com.example.demo.model.Categorie;
import com.example.demo.model.Produit;
import com.example.demo.repo.ProduitRepo;
import com.example.demo.repo.PromotionRepo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.repo.CategorieRepo;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@SpringBootApplication
public class Demo1Application implements CommandLineRunner{


    CategorieRepo categorieRepo;
    ProduitRepo produitRepo;

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }



    @SneakyThrows
    @Override
    public void run(String... args) throws Exception {



    }


    //Mis en caché suite au test
    /*
    @SneakyThrows
    @Override
    public void run(String... args) throws Exception {


     */




        /*
        log.info ( "Suppression toutes les categories de la liste et les produits" );
        produitRepo.deleteAll();
        categorieRepo.deleteAll ( );


        log.info ( "Flush" );
        produitRepo.flush();
        categorieRepo.flush ( );

         */

        //log.info("Ajout de categories");
        //categorieRepo.save(Categorie.builder().libelle("Meuble").build());
    }
/*
        log.info("Affichage de l'id de la categorie Smartphone");
        System.out.println(categorieRepo.findByLibelle("Smartphone").getId());



        produitRepo.save(Produit.builder().libelle("Iphone Serge").description("126 go de mémoire et processeur M2").prix(1000).idCategorie(categorieRepo.findByLibelle("Smartphone").getId()).build());


        log.info ( "La liste de toutes les catégories" );

        for ( Categorie categorie : categorieRepo.findAll ( ) ) {

            System.out.println ( "Liste de categorie = : " + categorie );

        }

        log.info ( "La liste de tous les produits" );

        for ( Produit produit : produitRepo.findAll ( ) ) {

            System.out.println ( "Liste de categorie = : " + produit );

        }

        log.info("Affichage de l'id de la categorie Smartphone");
            System.out.println(categorieRepo.findByLibelle("Smartphone").getId());


        log.info ( "Suppression toutes les categories de la liste et les produits" );
        produitRepo.deleteAll();
        categorieRepo.deleteAll ( );


        log.info ( "Flush" );
        produitRepo.flush();
        categorieRepo.flush ( );


    }
}



 */