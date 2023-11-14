package com.example.demo;

import com.example.demo.pojo.Categorie;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.service.CategorieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Demo2Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategorieRepoTest {

    @Autowired
    private ProduitRepository produitRepo;

    @Autowired
    private CategorieRepository categorieRepo;

    @Autowired
    private CategorieService categorieService;

    @Test
    public void createAndSaveCategorie(){
        String libelle = "pain";
        Categorie categorie = new Categorie();
        categorie.setLibelle(libelle);
        categorieRepo.save(categorie);
        assertEquals(1, categorieRepo.count());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveCategorieSameName() {

        String libelle = "test1";
        Categorie categorie = new Categorie();
        categorie.setLibelle(libelle);
        categorieRepo.save(categorie);

        Categorie categorie2 = new Categorie();
        categorie2.setLibelle(libelle);
        categorieRepo.save(categorie2);
    }

    /*@Test
    public void updateCategorie(){
        String libelle1 = "test1";
        Categorie categorie = new Categorie();
        categorie.setLibelle(libelle1);
        categorieRepo.save(categorie);

        String libelle2 = "test2";
        Categorie newcategorie = new Categorie();

        int id = categorieRepo.findByLibelle(libelle1).getId();

        categorieService.updateCategorie(newcategorie,1L);
        assertEquals(1,categorieRepo.findByLibelle("test2"));

    }

     */


}
