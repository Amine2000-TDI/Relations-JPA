package com.ezzahi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adresse {
    @Id
    @GeneratedValue
    private Long id;
    private String rue;
    private String codePostale;
    private String ville;
    //si je veux importe la personne et que la relation soit bidirectionnel
    @OneToOne(mappedBy = "adresse")
    private Personne personne;
    @Override
    public String toString(){
        return "Adresse : ( id = "+id+", rue = "+rue+", code postale = "+codePostale+", ville = "+ville+")";
    }
}
