package com.example.demo.service;

import com.example.demo.pojo.Produit;
import com.example.demo.pojo.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PromotionService {
    List<Promotion> getAllPromotions();

    void addPromotion(Promotion promotion, Long id);

    List<Produit> getProduitEnPromotionTodayMethod();

    List<Produit> getALLProduitsEnPromotionToday();

    List<Promotion> getAllPromotionsByIdProduit(Long id);

    boolean isProduitalreadyOnSalethatDate(Long id, Promotion promotion);
}
