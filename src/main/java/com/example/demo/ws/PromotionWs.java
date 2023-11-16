package com.example.demo.ws;

import com.example.demo.pojo.Produit;
import com.example.demo.pojo.Promotion;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.TEST + ApiRegistration.PROMOTION)
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class PromotionWs {

    @Autowired
    PromotionService promotionService;


    @GetMapping
    public List<Promotion> getAllPromotions(){
        return promotionService.getAllPromotions();
    }

    @GetMapping(ApiRegistration.TODAY)
    public List<Produit> getAllProduitsEnPromotionToday() {
        return promotionService.getALLProduitsEnPromotionToday();
    }


    @PostMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void addPromotion(@RequestBody Promotion promotion, @PathVariable Long id){
       promotionService.addPromotion(promotion, id);
    }
}
