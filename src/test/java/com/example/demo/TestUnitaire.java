package com.example.demo;

import com.example.demo.Controller.RestController;
import com.example.demo.model.Categorie;
import com.example.demo.repo.CategorieRepo;
import com.example.demo.repo.ProduitRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TestUnitaire {

    @InjectMocks
    private final RestController restController = new RestController();
    @Mock
    private ProduitRepo produitRepo;

    @Mock
    private CategorieRepo categorieRepo;


    @Test
    public void testGetAllProduitsByCategorie(){
        Optional<Categorie> optionalCategorie = categorieRepo.findById(1);
        Categorie categorie =   optionalCategorie.get();
        assertEquals(categorie.getLibelle(),"Epicerie");
    }
}
