package com.example.demo;

import com.example.demo.model.Categorie;
import com.example.demo.model.Produit;
import com.example.demo.model.Promotion;
import com.example.demo.repo.CategorieRepo;
import com.example.demo.repo.ProduitRepo;
import com.example.demo.repo.PromotionRepo;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
@SpringBootTest
class Demo1ApplicationTests
{
    @Autowired
    CategorieRepo categorieRepo;

    @Autowired
    ProduitRepo produitRepo;

    @Autowired
    PromotionRepo promotionRepo;


    //creation de categorie et de produit en même temps
    @Test
    public void initProduitEntity(){


        Categorie categorie = new Categorie();
        categorie.setLibelle("Produits de Corse");
        categorieRepo.save(categorie);

        Produit produit = new Produit();
        System.out.println(produit.getId());

        produit.setLibelle("Saucisson");
        produit.setDescription("De Montagne");
        produit.setPrix(10);
        produit.setCategorie(categorie);

        Produit saved = produitRepo.save(produit);
        assertEquals(saved.getLibelle(), produit.getLibelle());
    }

    @Test
    public void initProduitEntityWithExistingCategory(){

        Optional<Categorie> optionalCategorie = categorieRepo.findById(2);
        Categorie categorie =   optionalCategorie.get();
        System.out.println(categorie);

        Produit produit = new Produit();
        produit.setLibelle("Jus de Poire");
        produit.setDescription("De La Mer");
        produit.setPrix(15);
        produit.setCategorie(categorie);

        Produit saved = produitRepo.save(produit);
        assertEquals(saved.getLibelle(), produit.getLibelle());


    }

    @Test
    public void addPromotionToProduit(){
        //recupération du produit avec l'id 1 pour tester si la récupération fonctionne
        Optional<Produit> produitOptional = produitRepo.findById(4);
        Produit produitPromo = produitOptional.get();
        produitPromo.setEnPromotion(true);
        System.out.println(produitPromo);
        produitRepo.save(produitPromo);

        //creation de la promotion et initialisation
        Promotion promotion = new Promotion();
        promotion.setProduit(produitPromo);
        promotion.setTaux(25);
        promotion.setDatePromotion(new Date(1990, 10, 26, 0, 0));

        Promotion saved = promotionRepo.save(promotion);
        assertEquals(saved.getTaux(), promotion.getTaux());

    }
    @Test
    public void getListProduitByCategorie(){
        //Définition de la catégorie existante en BDD et du nombre de produits éxistants en BDD
        //de cette catégorie
        int IdCategorie = 1;
        int numProducWithThatId = 3;
        Optional<Categorie> optionalCategorie = categorieRepo.findById(IdCategorie);
        if (optionalCategorie.isPresent()){
            Categorie categorie = optionalCategorie.get();
            List<Produit> produits = new ArrayList<>();
            produits = produitRepo.findByCategorie(categorie);
            System.out.println(produits);
            assertEquals(numProducWithThatId, produits.size());
        }
    }

}
