package com.example.demo.service.impl;

import com.example.demo.pojo.Categorie;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.service.CategorieService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getCategorieByLibelle(String libelle) {
        return categorieRepository.findByLibelle(libelle);
    }

    @Override
    public Categorie getOrSaveCategorie(Categorie categorie){
        if (categorie == null || StringUtils.isEmpty(categorie.getLibelle())){
            throw new IllegalArgumentException("La catégorie est nulle sans libelle");
        }
        Categorie categorieSaved = categorieRepository.findByLibelle(categorie.getLibelle());
        if (categorieSaved == null){
            categorieSaved = categorieRepository.save(categorie);
        }
        return categorieSaved;
    }

    @Override
    public void addCategorie(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    @Override
    public void updateCategorie(Categorie categorie, Long id) {
        Categorie oldCategorie = getCategorieById(id);

        if (oldCategorie != null) {
            oldCategorie.setLibelle(categorie.getLibelle());
            categorieRepository.save(oldCategorie);
        }
    }

    @Override
    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategorieById(Long id) {
        categorieRepository.deleteById(id);
    }

}
