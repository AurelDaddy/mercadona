package com.example.demo.repository;

import com.example.demo.model.Categorie;
import com.example.demo.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit, Integer> {

    <S extends Produit> S save(S produit);

    long count();

    List<Produit> findByCategorie(Categorie categorie);


/*
    Produit findByPrix(int prix);z

    @Query("SELECT produitCher " +
            "FROM Produit produitCher " +
            "WHERE UPPER(produitCher.marque) LIKE %:marque%  ")
    Produit trouverUnProduitSelonMesPreferences(@Param("marque") String marque);

    List<Produit> findAll();
    List<Produit> findAll(Sort sort);
    void deleteAll();
    void flush();

     */
}