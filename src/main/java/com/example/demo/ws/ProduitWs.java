package com.example.demo.ws;

import com.example.demo.documentState.ProduitState;
import com.example.demo.pojo.Produit;
import com.example.demo.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.TEST + ApiRegistration.ALL + ApiRegistration.PRODUIT) // -> localhost:8080/api/produit/
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProduitWs {

    @Autowired
    private ProduitService produitService;

    @GetMapping(ApiRegistration.STATUS + "{produitState}")
    public List<Produit> getAllProduitsByState(@PathVariable ProduitState produitState){
        return produitService.getAllProduitsByState(produitState);
    }

    @GetMapping
    public List<Produit> getAllProduits(){
        return produitService.getAllProduits();
    }

    @GetMapping("{id}")
    public Produit getProduitById(@PathVariable Long id){
        return produitService.getProduitById(id);
    }

    @GetMapping(ApiRegistration.CATEGORIE + "{id}")
    public List<Produit> getAllProduitsByCategorie(@PathVariable Long id){
        return produitService.getAllProduitsByCategorie(id);
    }

    @PostMapping
    public void addProduit(@RequestBody Produit produit){
        produitService.addProduit(produit);
    }

    @DeleteMapping("{id}")
    public void deleteProduitById(@PathVariable Long id){
        produitService.deleteProduit(id);
    }

    @PutMapping("{id}")
    public void updateProduitById(@RequestBody Produit produit, @PathVariable Long id){
        produitService.updateProduit(id, produit);
    }



}
