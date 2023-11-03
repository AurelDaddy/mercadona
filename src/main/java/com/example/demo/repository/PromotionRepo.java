package com.example.demo.repository;

import com.example.demo.model.Produit;
import com.example.demo.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {
    <S extends Promotion> S save(S Promotion);


    List<Promotion> findAllByProduit(Produit produit);
}