package com.example.demo.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    LocalDate datePromotion;
    Integer taux;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_produit")
    Produit produit;

    public void setTaux(Integer taux) {
        if (taux < 5 || taux > 90) {
            throw new RuntimeException("le % de réduction doit être compris entre 5% et 90%");
        } else {
            this.taux = taux;
        }
    }

    public void setDatePromotion(LocalDate datePromotion) {
        if (datePromotion.isBefore(LocalDate.now())){
            throw new RuntimeException("Il n'est pas possible d'enregistrer une date de promotion dans le passé");
        }
        this.datePromotion = datePromotion;
    }
}
