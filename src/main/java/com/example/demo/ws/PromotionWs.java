package com.example.demo.ws;

import com.example.demo.pojo.Produit;
import com.example.demo.pojo.Promotion;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.TEST + ApiRegistration.ALL + ApiRegistration.PROMOTION)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public void addPromotion(@RequestBody Promotion promotion, @PathVariable Long id){
       promotionService.addPromotion(promotion, id);
    }
}
