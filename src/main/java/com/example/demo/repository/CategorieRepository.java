package com.example.demo.repository;


import com.example.demo.pojo.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    <S extends Categorie> S save(S categorie);

    @Override
    void deleteAll();

    @Override
    void flush();

    @Override
    List<Categorie> findAll();

    Categorie findByLibelle(String libelle);

}
