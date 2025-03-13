package com.ezzahi;

import com.ezzahi.dao.*;
import com.ezzahi.models.Adresse;
import com.ezzahi.models.Categorie;
import com.ezzahi.models.Personne;
import com.ezzahi.models.Projet;


import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Idao adresseDao = new AdresseDao();
        Idao categorieDao = new CategorieDao();
        Idao personneDao = new PersonneDao();
        Idao projetDao = new ProjetDao();

        // _______________________________________________________________ saisir les informartions _______________________________________________________________________

        Adresse adresse = (Adresse) adresseDao.save(Adresse.builder().rue("rue 1").codePostale("1").ville("Ville 1").build());
        Adresse adresse2 = (Adresse) adresseDao.save(Adresse.builder().rue("rue 2").codePostale("2").ville("Ville 2").build());

        Categorie categorie = (Categorie) categorieDao.save(Categorie.builder().labelle("categorie 1").description("description 1").build());
        Categorie categorie2 = (Categorie) categorieDao.save(Categorie.builder().labelle("categorie 2").description("description 2").build());

        Personne personne =(Personne) personneDao.save(Personne.builder().prenom("ezzahi").nom("amine").adresse(adresse).build());
        Personne personne2 =(Personne) personneDao.save(Personne.builder().prenom("ali").nom("rahou").adresse(adresse2).build());

        List<Projet> projets = Arrays.asList(
                Projet.builder().titre("projet 1").categorie(categorie).description("description projet 1").personnes(Arrays.asList(personne)).build(),
                Projet.builder().titre("projet 2").categorie(categorie).description("description projet 2").personnes(Arrays.asList(personne)).build(),
                Projet.builder().titre("projet 3").categorie(categorie).description("description projet 3").personnes(Arrays.asList(personne)).build()
        );
        List<Projet> projets2 = Arrays.asList(
                Projet.builder().titre("projet 4").categorie(categorie2).description("description projet 4").build(),
                Projet.builder().titre("projet 5").categorie(categorie2).description("description projet 5").build(),
                Projet.builder().titre("projet 6").categorie(categorie2).description("description projet 6").build()
        );

        for (int i = 0; i < projets.size(); i++) {
            projets.set(i, (Projet) projetDao.save(projets.get(i)));
        }
        for (int i = 0; i < projets2.size(); i++) {
            projets2.set(i, (Projet) projetDao.save(projets2.get(i)));
        }

        personne.setProjets(projets);
        personneDao.save(personne);

        personne2.setProjets(projets2);
        personneDao.save(personne2);



        // _______________________________________________________________ afficher les entité par id  _______________________________________________________________________
        System.out.println("_________________________________________ afficher les entité par id _________________________________________________");
        System.out.println("_________________________________________ getByID (adresse) _________________________________________________");
        System.out.println(adresseDao.getById(1L));
        System.out.println("_________________________________________ getByID (categorie) _________________________________________________");
        System.out.println(categorieDao.getById(3L));
        System.out.println("_________________________________________ getByID (personne) _________________________________________________");
        System.out.println(personneDao.getById(6L));
        System.out.println("_________________________________________ getByID (projet) _________________________________________________");
        System.out.println(projetDao.getById(12L));

        // _______________________________________________________________ supprimer les entité par id  _______________________________________________________________________
        System.out.println("_________________________________________ supprimer les entité par id _________________________________________________");
        System.out.println("_________________________________________ Delete (adresse) _________________________________________________");
        adresseDao.remove(1L);
        System.out.println("_________________________________________ Delete (categorie) _________________________________________________");
        categorieDao.remove(3L);
        System.out.println("_________________________________________ Delete (personne) _________________________________________________");
        personneDao.remove(6L);
        System.out.println("_________________________________________ Delete (projet) _________________________________________________");
        projetDao.remove(12L);

        // _______________________________________________________________ afficher toutes les informartions _______________________________________________________________________
        System.out.println("_________________________________________ afficher toutes les information __________________________________________________");
        System.out.println("________________________________________________ getAll (adresse) __________________________________________");
        adresseDao.getAll().forEach(System.out::println);
        System.out.println("_____________________________________________ getAll() (category)______________________________________________");
        categorieDao.getAll().forEach(System.out::println);
        System.out.println("__________________________________________________ getAll() (personne) _________________________________________");
        personneDao.getAll().forEach(System.out::println);
        System.out.println("_________________________________________ getAll (projet) __________________________________________________");
        projetDao.getAll().forEach(System.out::println);



    }
}