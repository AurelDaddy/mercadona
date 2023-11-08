package com.example.demo.service.impl;

import com.example.demo.pojo.Produit;
import com.example.demo.pojo.Promotion;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.service.PromotionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private PromotionService promotionService;

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public void addPromotion(Promotion promotion, Long id) {
        Produit produit = produitRepository.findById(id).orElse(null);
        log.info("le produit a été récupéré par son id");
        System.out.println(produit);
        System.out.println(promotion);
        if (this.isProduitalreadyOnSalethatDate(id, promotion)) {     //this a la place de promotionService
            log.info("le produit est déja en solde ce jour");
            throw new IllegalArgumentException("le produit est déja en solde ce jour");
        }
            if (produit == null) {
                log.info("le produit est nul");
                throw new IllegalArgumentException("Le produit est nul");
            }
                if (promotion == null) {
                    log.info("la promotion est nulle");
                    throw new IllegalArgumentException("la promotion est nulle");
                }
                    Promotion promotionTemp = new Promotion();
                    promotionTemp.setDatePromotion(promotion.getDatePromotion());
                    promotionTemp.setTaux(promotion.getTaux());
                    promotionTemp.setProduit(produit);
                    promotionRepository.save(promotionTemp);
                    log.info("la promotion a été enregistrée");
    }


    public List<Produit> getProduitEnPromotionTodayMethod() {
        List<Produit> produits = new ArrayList<>();
        List<Promotion> promotions = getAllPromotions();
        LocalDate dateDuJour = LocalDate.now();

        for(Promotion promotion : promotions){
            //if (promotion.getDatePromotion() == dateDuJour) {
            if (dateDuJour.equals(promotion.getDatePromotion())) {
                produits.add(promotion.getProduit());
            }
        }
        return produits;
    }

    @Override
    public List<Produit> getALLProduitsEnPromotionToday() {
        List<Produit> produits = getProduitEnPromotionTodayMethod();
        return produits;
    }

    @Override
    public List<Promotion> getAllPromotionsByIdProduit(Long id) {

        Produit produit = produitRepository.findById(id).orElse(null);
        if (produit == null) {
            throw new IllegalArgumentException("La produit est nul");
        }
        return promotionRepository.findAllByProduit(produit);
    }
    @Override
    public boolean isProduitalreadyOnSalethatDate(Long id, Promotion newPromo){
        List<Promotion> promotions = getAllPromotionsByIdProduit(id);
        System.out.println(promotions);
        if (promotions != null){
            for (Promotion promotion : promotions) {
                if (promotion.getDatePromotion().equals(newPromo.getDatePromotion())){
                    return true;
                }
            }
        }
        return false;
    }
}
