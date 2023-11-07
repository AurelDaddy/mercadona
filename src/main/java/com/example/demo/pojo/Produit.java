package com.example.demo.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    boolean promotion = false;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_categorie")
    Categorie categorie;

    public void setPrix(float prix) {
        if (prix <= 1) {
            throw new RuntimeException("Le prix ne peut être inférieur à 1€");
        } else {
            this.prix = prix;
        }
    }



}
