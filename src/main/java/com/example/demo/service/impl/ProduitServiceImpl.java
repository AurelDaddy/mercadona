package com.example.demo.service.impl;

import com.example.demo.documentState.ProduitState;
import com.example.demo.pojo.Categorie;
import com.example.demo.pojo.Produit;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.service.CategorieService;
import com.example.demo.service.ProduitService;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Produit> getAllProduitsByState(ProduitState produitState) {

        List<Produit> produits = new ArrayList<>();

        switch (produitState) {
            case TOUS -> produits = produitRepository.findAll();
            case PROMOTION -> produits = produitRepository.findAllByPromotion(true);
            case NO_PROMOTION -> produits = produitRepository.findAllByPromotion(false);
        }
        return produits;
    }

    @Override
    public List<Produit> getAllProduits() {

        List<Produit> allProduits = produitRepository.findAll();
        //récupération des produits en promotion today
        List<Produit> produitsEnPromotion = promotionService.getProduitEnPromotionTodayMethod();
        //Set promotion à True pour les produits en promotions
        for (Produit produit : allProduits) {
            for (Produit produitPromo : produitsEnPromotion) {
                if (produitPromo.equals(produit)) {
                    produit.setPromotion(true);
                }
            }
        }
        return allProduits;
    }


    @Override
    public void addProduit(Produit produit) {

        if (produit == null) {
            throw new IllegalArgumentException("La produit est nul");
        }
        //recherche dans la base de donnée si la catégorie existe
        Categorie categorie = categorieService.getOrSaveCategorie(produit.getCategorie());

        Produit produitNew = new Produit();
        produitNew.setLibelle(produit.getLibelle());
        produitNew.setDescription(produit.getDescription());
        produitNew.setPrix(produit.getPrix());
        produitNew.setCategorie(categorie);

        produitRepository.save(produitNew);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public void updateProduit(Long id, Produit produit) {
        Produit oldProduit = getProduitById(id);

        if (oldProduit != null) {
            oldProduit.setLibelle(produit.getLibelle());
            oldProduit.setDescription(produit.getDescription());
            oldProduit.setPromotion(produit.isPromotion());
            oldProduit.setCategorie(produit.getCategorie());
            oldProduit.setPrix(produit.getPrix());
            produitRepository.save(oldProduit);
        }
    }

    @Override
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Produit> getAllProduitsByCategorie(Long id) {
        List<Produit> produits = new ArrayList<>();
        if (categorieRepository.findById(id).isPresent()) {
            produitRepository.findAll().forEach((Produit produit) -> {
                if (produit.getCategorie().getId() == id) {
                    produits.add(produit);
                }
            });
        }
        return produits;
    }
}


