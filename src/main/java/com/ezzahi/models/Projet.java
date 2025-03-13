package com.ezzahi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Projet {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String titre;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cle_etrangere_categorie" )
    private Categorie categorie;

    @ManyToMany(mappedBy = "projets", fetch = FetchType.LAZY )
    private List<Personne> personnes;

    @Override
    public String toString(){
        return "Projet : ( id = "+id+", description = "+description+", titre = "+titre+", categorie = "+categorie+")";
    }
}
