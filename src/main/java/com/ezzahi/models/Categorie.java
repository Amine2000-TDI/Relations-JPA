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
public class Categorie {
    @Id
    @GeneratedValue
    private Long id;
    private String labelle;
    private String description;

    @OneToMany(mappedBy = "categorie" , fetch = FetchType.LAZY)
    private List<Projet> projets;

    @Override
    public String toString(){
        return "Categorie : ( id = "+id+", labelle = "+labelle+" ,description = "+description+")";
    }
}
