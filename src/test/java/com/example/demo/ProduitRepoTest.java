package com.example.demo;

import com.example.demo.pojo.Categorie;
import com.example.demo.pojo.Produit;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
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
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Demo2Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProduitRepoTest {

    @Autowired
    private ProduitRepository produitRepo;

    @Autowired
    private CategorieRepository categorieRepo;


    Random random = new SecureRandom();

    private  Categorie categorie = new Categorie();
    void setup() {

        categorie.setLibelle(RandomStringUtils.randomAlphabetic(10));
        categorieRepo.save(categorie);


    }

    @Test
    public void saveProduit_thenOK() {

        setup();

        List<Produit> produitResults = produitRepo.findByCategorie(categorie);
        assertNotNull(produitResults);
        assertEquals(0, produitResults.size());

        Produit produit = new Produit();
        produit.setLibelle(RandomStringUtils.randomAlphabetic(10));
        produit.setDescription(RandomStringUtils.randomAlphabetic(20));
        produit.setPrix(100);
        produit.setCategorie(categorie);
        produitRepo.save(produit);

        produitResults = produitRepo.findByCategorie(categorie);
        assertNotNull(produitResults);
        assertEquals(1, produitResults.size());
        assertEquals(produitResults.get(0).getId(),produit.getId());

    }


    @Test(expected = RuntimeException.class)
    public void addProduitPrixNegatif() {
        float prix = -3;
        Produit produit = new Produit();
        produit.setPrix(prix);
        produitRepo.save(produit);
    }

    @Test
    public void addProduitPrixPositif() {
        float prix = 100;
        Produit produit = new Produit();
        produit.setPrix(prix);
        produitRepo.save(produit);
        assertEquals(1, produitRepo.count());
    }


}
