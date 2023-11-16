package com.example.demo;

import com.example.demo.pojo.Categorie;
import com.example.demo.pojo.Produit;
import com.example.demo.pojo.Promotion;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.service.PromotionService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Demo2Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PromotionRepoTest {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProduitRepository produitRepo;

    @Autowired
    private CategorieRepository categorieRepo;

    @Autowired
    private PromotionRepository promotionRepo;

    private Categorie categorie = new Categorie();

    private Produit produit = new Produit();
    Random random = new SecureRandom();

    void setup() {

        categorie.setLibelle(RandomStringUtils.randomAlphabetic(10));
        categorieRepo.save(categorie);
        produit.setLibelle(RandomStringUtils.randomAlphabetic(10));
        produit.setDescription(RandomStringUtils.randomAlphabetic(20));
        produit.setPrix(100);
        produit.setCategorie(categorie);
        produitRepo.save(produit);
    }

    @Test
    public void savePromotionExistingProduit_thenOK() {
        setup();

        assertEquals(0, promotionRepo.count());
        //creation première promotion
        Promotion promotion = new Promotion();
        promotion.setTaux(40);
        promotion.setDatePromotion(LocalDate.now());


        Optional<Produit> produitOptional = produitRepo.findById(1L);
        Produit produitEnPromotion = produitOptional.get();
        promotion.setProduit(produitEnPromotion);
        promotionRepo.save(promotion);
        assertEquals(1, promotionRepo.count());
    }



    @Test(expected = RuntimeException.class)
    public void addPromotionTauxHigherThan90Percent() {
        setup();
        int taux = 95;
        Promotion promotion = new Promotion();
        promotion.setTaux(taux);
        Optional<Produit> produitOptional = produitRepo.findById(1L);
        Produit produit = produitOptional.get();
        promotion.setProduit(produit);
        promotionRepo.save(promotion);
    }


    @Test(expected = RuntimeException.class)
    public void addPromotionTauxLowerThan5Percent() {
        setup();
        int taux = 3;
        Promotion promotion = new Promotion();
        promotion.setTaux(taux);
        Optional<Produit> produitOptional = produitRepo.findById(1L);
        Produit produit = produitOptional.get();
        promotion.setProduit(produit);
        promotionRepo.save(promotion);
    }


    @Test
    public void addPromotionTauxCorrect() {

        setup();
        int taux = 20;
        Promotion promotion = new Promotion();
        promotion.setTaux(taux);
        Optional<Produit> produitOptional = produitRepo.findById(1L);
        Produit produit = produitOptional.get();
        promotion.setProduit(produit);
        promotionRepo.save(promotion);
        assertEquals(1, promotionRepo.count());
    }


    @Test
    public void getListPromotionOfProduit() {
        setup();

        Optional<Produit> produitOptional = produitRepo.findById(1L);
        Produit produit = produitOptional.get();
        //création premiere promotion
        Promotion promotion = new Promotion();
        promotion.setTaux(20);
        promotion.setProduit(produit);
        promotion.setDatePromotion(LocalDate.now());
        promotionRepo.save(promotion);

        //création deuxième promotion
        Promotion promotion1 = new Promotion();
        promotion1.setTaux(20);
        promotion1.setProduit(produit);
        promotion1.setDatePromotion(LocalDate.now().plusDays(1));
        promotionRepo.save(promotion1);

        assertEquals(2, promotionRepo.count());
    }


    @Test
    public void getListPromotionToday(){
        setup();

        Optional<Produit> produitOptional = produitRepo.findById(1L);
        Produit produit = produitOptional.get();
        //Création premiere promotion date du jour
        Promotion promotion = new Promotion();
        promotion.setTaux(20);
        promotion.setProduit(produit);
        promotion.setDatePromotion(LocalDate.now());
        promotionRepo.save(promotion);
        assertEquals(1, promotionService.getProduitEnPromotionTodayMethod().size());

    }

    @Test(expected = RuntimeException.class)
    public void addPromotionSameDateSameProduit() {
        setup();

        //creation première promotion today
        Promotion promotion = new Promotion();
        promotion.setTaux(40);
        promotion.setDatePromotion(LocalDate.now());

        Optional<Produit> produitOptional = produitRepo.findById(1L);
        Produit produit = produitOptional.get();
        promotion.setProduit(produit);
        promotionRepo.save(promotion);

        //creation deuxième promotion today
        Promotion promotion1 = new Promotion();
        promotion1.setTaux(40);
        promotion1.setDatePromotion(LocalDate.now());
        promotion1.setProduit(produit);
        promotionService.addPromotion(promotion1,1L);
    }
}


