package com.example.demo.service;

import com.example.demo.pojo.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> getAllCategories();

    Categorie getCategorieByLibelle(String libelle);

    Categorie getOrSaveCategorie(Categorie categorie);

    void addCategorie(Categorie categorie);

    void updateCategorie(Categorie categorie, Long id);

    Categorie getCategorieById(Long id);

    void deleteCategorieById(Long id);
}
