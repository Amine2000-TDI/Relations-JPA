﻿<h1>Les Relations en JPA (OneToOne, OneToMany, ManyToMany)</h1>
<h2>Premiére commit 1 :</h2>
<h3>Préparation de l'environnement :</h3>
<p>Ajout des dépendances nécessaires dans le fichier pom.xml, configuration de la base de données via le fichier persistence.xml, et mise en place de la gestion des logs avec log4j.</p>
<h2>Deuxième commit 2 :</h2>
<h3>Les relations</h3>
<p>Ce projet met en œuvre les relations OneToOne, OneToMany et ManyToMany en JPA avec Java SE et Hibernate. Il inclut plusieurs entités avec des relations bidirectionnelles, un mode de chargement paresseux (FetchType.LAZY) pour optimiser la récupération des données et l'utilisation de CascadeType.ALL pour propager certaines opérations.</p>
<ul>
<li>Une Personne possède une Adresse (OneToOne) avec une suppression en cascade.</li>
<li>Une Personne peut participer à plusieurs Projets et vice versa (ManyToMany).</li>
<li>Un Projet appartient à une Catégorie (ManyToOne), et une Catégorie peut contenir plusieurs Projets (OneToMany).</li>
</ul>
<h2>Troisième commit 3 :</h2>
<h3>La couche DAO avec JPQL</h3>
<ol>
<li>Création d'une couche DAO pour centraliser l'accès aux données.</li>
<li>Implémentation d'une interface générique IDAO<T, ID> contenant les méthodes essentielles (save, update, delete, findById, findAll).</li>
<li>Chaque entité (PersonneDao, AdresseDao...) implémente IDAO, ce qui standardise l'accès aux données.</li>
<li>Gestion des transactions avec EntityManager, tout en utilisant merge() pour persister les entités.</li>
</ol>
