package com.example.demo.repository;

import com.example.demo.pojo.Categorie;
import com.example.demo.pojo.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    <S extends Produit> S save(S produit);

    long count();

    List<Produit> findByCategorie(Categorie categorie);

    List<Produit> findAllByPromotion(boolean value);

}