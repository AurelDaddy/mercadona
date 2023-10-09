package com.example.demo.Controller;


import com.example.demo.model.Categorie;
import com.example.demo.model.Produit;
import com.example.demo.model.Promotion;
import com.example.demo.model.User;
import com.example.demo.repo.CategorieRepo;
import com.example.demo.repo.ProduitRepo;
import com.example.demo.repo.PromotionRepo;
import com.example.demo.service.ProduitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/mercadona")
public class RestController {
    @Autowired
    private ProduitService service;

    @Autowired
    ProduitRepo produitRepo;

    @Autowired
    CategorieRepo categorieRepo;

    @Autowired
    PromotionRepo promotionRepo;

    @Transactional
    @PostMapping("/addProduit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Produit addProduit(@RequestBody Produit produit) {
        if (produit == null){
            throw new IllegalArgumentException("La produit est nul");
        }
        //recherche dans la base de donnée si la catégorie existe
        Categorie categorie = getOrSaveCategorie(produit.getCategorie());

        Produit produitNew = new Produit();
        produitNew.setLibelle(produit.getLibelle());
        produitNew.setDescription(produit.getDescription());
        produitNew.setPrix(produit.getPrix());
        produitNew.setEnPromotion(produit.isEnPromotion());
        produitNew.setCategorie(categorie);

        return produitRepo.save(produitNew);
        //ResponseEntity.ok("Data saved");
    }

    @PostMapping("/addCategorie")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return getOrSaveCategorie(categorie);
    }

    @PutMapping("putPromotiondates/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Promotion>> updateProduits(@PathVariable int id, @RequestBody List<Promotion> promotions) {
        Optional<Produit> produitOptional = produitRepo.findById(id);

        //rajouter si l'id du produit est le meme que le produit dans promotion

        if (produitOptional.isPresent()) {
            Produit produitEnPromo = produitOptional.get();
            log.info("le produit éxiste");
            produitEnPromo.setEnPromotion(true);
            produitRepo.save(produitEnPromo);
            log.info("le booleen enPromotion du produit est passé à True");


            List<Promotion> listePromotions = new ArrayList<>();
            for (Promotion promotion1 : promotions) {
                //test si la variable promotion est pas nulle
                if (promotion1 == null) {
                    log.warning("Le produit n'a pu être mis à jour ni la promotion créée");
                    return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Promotion promotionTemp = new Promotion();
                promotionTemp.setDatePromotion(promotion1.getDatePromotion());
                promotionTemp.setTaux(promotion1.getTaux());
                promotionTemp.setProduit(produitEnPromo);

                log.info("la promotion est bien définie");
                promotionRepo.save(promotionTemp);

                //ajout à la liste des promotions pour le return de la ResponseEntity
                listePromotions.add(promotionTemp);

            }
            log.info("Le produit a été mis à jour en promotion et la promotion créée");
            return new ResponseEntity(listePromotions, HttpStatus.OK);
        }
        log.warning("Le produit est vide et n'a pas été mis en promotion");
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //RECUPERER TOUS LES PRODUITS

    @GetMapping("/")
    public ResponseEntity<List<Produit>> getAllProduit() {
        log.info("Affichage de tous les produits");
        List<Produit> allProduits = produitRepo.findAll();
        return ResponseEntity.ok(allProduits);
    }

    //RECUPERER TOUTES LES CATEGORIES
    @GetMapping("/categorie")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Categorie>> getAllCategorie() {
        log.info("Affichage de toutes les catégories");
        List<Categorie> allCategories = categorieRepo.findAll();
        return ResponseEntity.ok(allCategories);
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User userInfo){
        return service.addUser(userInfo);
    }


    //RECUPERER TOUS LES PRODUITS D UNE CATEGORIE

    @GetMapping("/categorie/{id}")
    public ResponseEntity<List<Produit>> getAllProduitByCategories(@PathVariable(value = "id") int categorieId ) {
        log.info("Méthode de gestion de requète HTPP pour trouver les produit d'une catégorie précise");
        List<Produit> produits = new ArrayList<>();
        if (categorieRepo.findById(categorieId).isPresent()) {
            produitRepo.findAll().forEach((Produit produit) -> {
                if (produit.getCategorie().getId() == categorieId) {
                    produits.add(produit);
                } /*else {
                    log.info("Il n'y a pas de produit de cette catégorie");
                }*/
            });
        } else{
            log.info("Cette catégorie n'éxiste pas");
        }
        return ResponseEntity.ok(produits);
    }


    //Méthode de vérification si la catégorie éxiste
    private Categorie getOrSaveCategorie(Categorie categorie){
        if (categorie == null || StringUtils.isEmpty(categorie.getLibelle())){
            //log.("la categorie est nulle");
            throw new IllegalArgumentException("La catégorie est nulle sans libelle");
        }
        Categorie categorieSaved = categorieRepo.findByLibelle(categorie.getLibelle());
        if (categorieSaved == null){
            log.info("catégorie enregistrée");
            categorieSaved = categorieRepo.save(categorie);
        }
        return categorieSaved;
    }








    /*
    @PostMapping("/addPromotion")
    public ResponseEntity<String> addPromotion(@RequestBody Promotion promotion) {
        promotionRepo.save(promotion);
        log.info(String.valueOf(promotion));
        return ResponseEntity.ok("Data saved");
    }
    */

    /*
    // mise à jour produit en promotion
    @PutMapping("putPromotion/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable int id, @RequestBody Produit produitDetails) {
        Optional<Produit> produitOptional = produitRepo.findById(id);
        if (produitOptional.isPresent()) {
            Produit updateProduit = produitOptional.get();


            //updateProduit.setLibelle(produitDetails.getLibelle());
            //updateProduit.setPrix(produitDetails.getPrix());
            //updateProduit.setCategorie(produitDetails.getCategorie());
            updateProduit.setEnPromotion(true);
            //updateProduit.setDescription(produitDetails.getDescription());

            produitRepo.save(updateProduit);
            log.info("Le produit a été mis à jour en promotion");
            return ResponseEntity.ok(updateProduit);
        }
        log.warning("Le produit n'a pu être mis à jour");
        return new ResponseEntity<>(produitDetails, HttpStatus.OK);
    }

     */

    @PutMapping("putPromotion2/{id}")
    public ResponseEntity<Promotion> updateProduit2(@PathVariable int id, @RequestBody Promotion promotion) {
        Optional<Produit> produitOptional = produitRepo.findById(id);

        //rajouter si l'id du produit est le meme que le produit dans promotion

        if (produitOptional.isPresent()) {
            Produit produitEnPromo = produitOptional.get();
            log.info("le produit éxiste");
            produitEnPromo.setEnPromotion(true);
            produitRepo.save(produitEnPromo);
            log.info("le booleen enPromotion du produit est passé à True");

            //test si la variable promotion est pas nulle
            if (promotion == null) {
                log.warning("Le produit n'a pu être mis à jour ni la promotion créée");
                return new ResponseEntity<>(promotion, HttpStatus.OK);
            }
            Promotion promotionTemp = new Promotion();
            promotionTemp.setDatePromotion(promotion.getDatePromotion());
            promotionTemp.setTaux(promotion.getTaux());
            promotionTemp.setProduit(produitEnPromo);

            log.info("la promotion est bien définie");
            promotionRepo.save(promotionTemp);
            log.info("Le produit a été mis à jour en promotion et la promotion créée");
            return ResponseEntity.ok(promotionTemp);
        }
        log.warning("Le produit est vide et n'a pas été mis en promotion");
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }














    /*
    @PostMapping("/addProduit")
    public void addProduit(@RequestParam("model") String jsonObject){
        //@RequestBody Produit produit){
        //controle sur le produit sinon retourne erreur avec les get
        //
        Produit produit = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           // String fileName = fileStorageService.storeFile(file);
           // ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString();
           // produit = objectMapper.readValue(jsonObject, User.class);
            // response.setImage(file.getOriginalFilename());
        produit = objectMapper.readValue(jsonObject, Produit.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        produitRepo.save(produit);
    }
    */


    @GetMapping("/test")
    public String test(){
        //controle sur le produit sinon retourne erreur avec les get
        //
        System.out.println("Ceci est un test");
        return "Coucou";


    }

}


