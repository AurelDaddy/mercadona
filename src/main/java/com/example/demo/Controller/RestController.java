package com.example.demo.Controller;


import com.example.demo.model.Categorie;
import com.example.demo.model.Produit;
import com.example.demo.model.Promotion;
import com.example.demo.repository.CategorieRepo;
import com.example.demo.repository.ProduitRepo;
import com.example.demo.repository.PromotionRepo;
//import com.example.demo.service.ProduitService;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/mercadona")
public class RestController {
    //@Autowired
    //private ProduitService service;

    @Autowired
    ProduitRepo produitRepo;

    @Autowired
    CategorieRepo categorieRepo;

    @Autowired
    PromotionRepo promotionRepo;

    @Transactional
    @PostMapping("/addProduit")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
        produitNew.setCategorie(categorie);

        return produitRepo.save(produitNew);
        //ResponseEntity.ok("Data saved");
    }

    @PostMapping("/addCategorie")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return getOrSaveCategorie(categorie);
    }

    @PutMapping("putPromotionListeDates/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Promotion>> updateProduits(@PathVariable int id, @RequestBody List<Promotion> promotions) {
        Optional<Produit> produitOptional = produitRepo.findById(id);

        if (produitOptional.isPresent()) {
            Produit produitEnPromo = produitOptional.get();
            log.info("le produit éxiste");

            List<Promotion> listePromotions = new ArrayList<>();
            for (Promotion promotion1 : promotions) {
                //test si la variable promotion n'est pas nulle et que la date de promotion du produit n'existe pas déjà
                if ((promotion1 == null) || (datePromotionDoesExist(promotion1.getDatePromotion(),produitEnPromo)))  {
                    log.warning("La promotion n'a pu être créée");
                    return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR); //changer la responseEntity
                } else if (Objects.equals(produitEnPromo.getLibelle(), promotion1.getProduit().getLibelle())) {
                    Promotion promotionTemp = new Promotion();
                    promotionTemp.setDatePromotion(promotion1.getDatePromotion());
                    promotionTemp.setTaux(promotion1.getTaux());
                    promotionTemp.setProduit(produitEnPromo);

                    log.info("la promotion est bien définie");
                    promotionRepo.save(promotionTemp);

                    //ajout à la liste des promotions pour le return de la ResponseEntity
                    listePromotions.add(promotionTemp);
                }
            }
            log.info("la liste de promotion a été créée");
            return new ResponseEntity(listePromotions, HttpStatus.OK);
        }
        log.warning("Le produit est vide aucune(s) promotion(s) de créée(s)");
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //RECUPERER TOUS LES PRODUITS

    @GetMapping("/")
    public ResponseEntity<List<Produit>> getAllProduit() {
        log.info("Affichage de tous les produits");
        //récupération de tous les produits
        List<Produit> allProduits = produitRepo.findAll();
        //récupération des produits en promotion today
        List<Produit> produitsEnPromotion = getProduitEnPromotionTodayMethod();
        //Set promotion à True pour les produits en promotions
        for (Produit produit : allProduits) {
            for (Produit produitPromo : produitsEnPromotion) {
                if(produitPromo.equals(produit)){
                    produit.setPromotion(true);
                }
            }
        }
        //renvoie de la liste de tous les produits (les produits en promotion sont notifiés)
        return ResponseEntity.ok(allProduits);
    }

    //RECUPERER TOUTES LES CATEGORIES
    @GetMapping("/categorie")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Categorie>> getAllCategorie() {
        log.info("Affichage de toutes les catégories");
        List<Categorie> allCategories = categorieRepo.findAll();
        return ResponseEntity.ok(allCategories);
    }
/*
    @PostMapping("/new")
    public String addNewUser(@RequestBody User userInfo){
        return service.addUser(userInfo);
    }


 */

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



    @PutMapping("put1Promotion/{id}")
    public ResponseEntity<Promotion> updateProduit2(@PathVariable int id, @RequestBody Promotion promotion) {
        Optional<Produit> produitOptional = produitRepo.findById(id);

        //rajouter si l'id du produit est le meme que le produit dans promotion

        if (produitOptional.isPresent()) {
            Produit produitEnPromo = produitOptional.get();
            log.info("le produit éxiste");

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
            log.info("La promotion créée");
            return ResponseEntity.ok(promotionTemp);
        }else {
            log.warning("Le produit est vide et n'a pas été mis en promotion");
        }
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        log.info("Affichage de toutes les promotions");
        List<Promotion> promotions = promotionRepo.findAll();
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/promotionsDateDuJour")
    public ResponseEntity<List<Produit>> getAllProduitsEnPromotionToday() {
        List<Produit> produits = getProduitEnPromotionTodayMethod();
        return ResponseEntity.ok(produits);
    }

    public List<Produit> getProduitEnPromotionTodayMethod() {
        log.info("Affichage de toutes les promotions");
        List<Produit> produits = new ArrayList<>();
        List<Promotion> promotions = promotionRepo.findAll();
        LocalDate dateDuJour = LocalDate.now();
        System.out.println(dateDuJour);

        for(Promotion promotion : promotions){
            System.out.println(promotion.getDatePromotion());

            //if (promotion.getDatePromotion() == dateDuJour) {
            if (dateDuJour.equals(promotion.getDatePromotion())) {
                produits.add(promotion.getProduit());
            }
        }
        return produits;
    }

    public boolean datePromotionDoesExist(LocalDate datePromotion, Produit produit) {
        //recupération de tous les promotions du produits
        List<Promotion> promotionsDuProduit = promotionRepo.findAllByProduit(produit);
        //test si la date de promotion éxiste déjà pour ce produit
        for (Promotion promotion : promotionsDuProduit){
            if (datePromotion.equals(promotion.getDatePromotion())) {
                return true;
            }
         }return false;
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


