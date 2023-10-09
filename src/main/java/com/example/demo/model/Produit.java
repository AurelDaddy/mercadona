package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "produit")

public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String libelle;
    String description;
    float prix;
    boolean enPromotion = false;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_categorie")
    Categorie categorie;

    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "produit")
    //Promotion promotion;





    /*Fonction à implementer
     *       -> Ajouter produit en fonction du formulaire prérempli
    *
    *   Produit ajouterProduit(Produit p){
    *        return p;
    *         }
    *
    *   ? ajouterPromotion(Produit p){
    *           this.promotion = true;
    *           this.dateDebutPromotion = %s (recup formulaire);
    *           this.dateFinPromotion = %s (recup formulaire);
    *
    *   return this;
    *
    *
    *
    *
    *
     **/



}
