package com.ezzahi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Personne {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;

    @OneToOne(cascade = CascadeType.ALL)
    //le nom de la clé étrangère
    @JoinColumn(name="cle_etrangere_adresse")
    private Adresse adresse;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Projet> projets;

    @Override
    public String toString(){
        return "Personne : ( id = "+id+", nom = "+nom+", prenom = "+prenom+", adresse = "+adresse+")";
    }
}
