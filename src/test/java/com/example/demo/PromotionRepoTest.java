package com.example.demo;

import com.example.demo.model.Categorie;
import com.example.demo.model.Produit;
import com.example.demo.model.Promotion;
import com.example.demo.repo.CategorieRepo;
import com.example.demo.repo.ProduitRepo;
import com.example.demo.repo.PromotionRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Demo2Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PromotionRepoTest {

    @Autowired
    private ProduitRepo produitRepo;

    @Autowired
    private CategorieRepo categorieRepo;

    @Autowired
    private PromotionRepo promotionRepo;

    Random random = new SecureRandom();

    @Test
    public void savePromotionExistingProduit_thenOK() {
        //setup();

        assertEquals(0, promotionRepo.count());
        //creation première promotion
        Promotion promotion = new Promotion();
        promotion.setTaux(40);
        promotion.setDatePromotion(LocalDate.now());

        Optional<Produit> produitOptional = produitRepo.findById(1);
        Produit produitEnPromotion = produitOptional.get();
        promotion.setProduit(produitEnPromotion);
        promotionRepo.save(promotion);
        assertEquals(1, promotionRepo.count());

        //création deuxième promotion
        Promotion promotion1 = new Promotion();
        promotion1.setTaux(40);
        promotion1.setDatePromotion(LocalDate.now());

        Optional<Produit> produitOptional1 = produitRepo.findById(2);
        Produit produitEnPromotion1 = produitOptional1.get();
        promotion1.setProduit(produitEnPromotion1);
        promotionRepo.save(promotion1);
        assertEquals(2, promotionRepo.count());
    }



    @Test(expected = RuntimeException.class)
    public void addPromotionTauxHigherThan90Percent() {
        int taux = 95;
        Promotion promotion = new Promotion();
        promotion.setTaux(taux);
        Optional<Produit> produitOptional = produitRepo.findById(1);
        Produit produit = produitOptional.get();
        promotion.setProduit(produit);
        promotionRepo.save(promotion);
    }


    @Test(expected = RuntimeException.class)
    public void addPromotionTauxLowerThan5Percent() {

        int taux = 3;
        Promotion promotion = new Promotion();
        promotion.setTaux(taux);
        Optional<Produit> produitOptional = produitRepo.findById(1);
        Produit produit = produitOptional.get();
        promotion.setProduit(produit);
        promotionRepo.save(promotion);
    }


    @Test
    public void addPromotionTauxCorrect() {

        int taux = 20;
        Promotion promotion = new Promotion();
        promotion.setTaux(taux);
        Optional<Produit> produitOptional = produitRepo.findById(1);
        Produit produit = produitOptional.get();
        promotion.setProduit(produit);
        promotionRepo.save(promotion);
        assertEquals(1, promotionRepo.count());
    }


    @Test
    public void getListPromotionOfProduit() {
        List<Promotion> promotions = new ArrayList<>();

        Optional<Produit> produitOptional = produitRepo.findById(1);
        Produit produit = produitOptional.get();
        //création premiere promotion
        Promotion promotion = new Promotion();
        promotion.setTaux(20);
        promotion.setProduit(produit);
        promotionRepo.save(promotion);

        //création deuxième promotion
        Promotion promotion1 = new Promotion();
        promotion1.setTaux(20);
        promotion1.setProduit(produit);
        promotionRepo.save(promotion1);

        assertEquals(2, promotionRepo.count());
    }


}


