package com.example.demo.ws;

import com.example.demo.pojo.Categorie;
import com.example.demo.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.CATEGORIE)
@RestController
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600, allowCredentials = "true")
public class CategorieWs {

    @Autowired
    CategorieService categorieService;

    @GetMapping
    public List<Categorie> getAllCategories(){
        return categorieService.getAllCategories();
    }

    @GetMapping("{id}")
    public Categorie getCategorieById(@PathVariable Long id){
        return categorieService.getCategorieById(id);
    }

    @PostMapping
        public void addCategorie(@RequestBody Categorie categorie){
            categorieService.addCategorie(categorie);
        }

    @PutMapping("{id}")
    public void updateCategorie(@RequestBody Categorie categorie, @PathVariable Long id){
            categorieService.updateCategorie(categorie, id);
    }
    /*
    @DeleteMapping("{id}")
        public void deleteCategorieById(@PathVariable Long id){
            categorieService.deleteCategorieById(id);
    }
     */

    }


