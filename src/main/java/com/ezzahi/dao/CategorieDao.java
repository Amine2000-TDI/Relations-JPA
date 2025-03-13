package com.ezzahi.dao;

import com.ezzahi.models.Categorie;
import com.ezzahi.models.Categorie;
import com.ezzahi.models.Personne;
import com.ezzahi.models.Projet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CategorieDao implements Idao<Long, Categorie> {
    private EntityManager session;
    private EntityTransaction tx;
    private Logger log = LogManager.getLogger(CategorieDao.class);
    @Override
    public Categorie save(Categorie categorie) {
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            tx.begin();
            categorie = session.merge(categorie);
            tx.commit();
            log.info("Save() Categorie OK "+categorie);
        } catch (Exception e) {
            tx.rollback();
            log.error("Save() Categorie KO "+e);
        }finally {
            session.close();
            return categorie;
        }
    }

    @Override
    public List<Categorie> getAll() {
        List<Categorie> result = null;
        try {
            session = SessionBuilder.getSession().createEntityManager();
            result = session.createQuery("from Categorie c").getResultList();
            log.info("getAll() Categories OK ");
        } catch (Exception e) {
            log.error("getAll() Categories KO "+e);
        }finally {
            session.close();
            return result;
        }
    }

    @Override
    public Categorie getById(Long id) {
        Categorie categorie = null;
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            categorie = session.find(Categorie.class,id);
            if(categorie != null){
                log.info("getById() Categorie OK "+categorie);
            }else {
                log.info("getById() OK but no Categorie has found because none withe the id = "+id);
            }
        } catch (Exception e) {
            log.error("getById()() Categorie KO "+e);
        }finally {
            session.close();
            return categorie;
        }
    }

    @Override
    public void remove(Long id) {
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            tx.begin();
            Categorie categorie = session.find(Categorie.class,id);
            if(categorie != null){
                List<Projet> projets = new ArrayList<>(categorie.getProjets());
                // on doit commencer par le maitre puis l'esclave
                List<Personne> personnes = session.createQuery("from Personne p").getResultList();
                for(Personne p : personnes){
                    p.getProjets().removeAll(projets);
                    session.merge(p);
                }
                for (Projet projet : projets) {
                    projet.getPersonnes().clear();
                    session.merge(projet);
                }
                session.remove(categorie);
                log.info("Remove() Categorie OK "+categorie);
            }else {
                log.info("Remove() OK but no Categorie has deleted because none withe the id = "+id);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("Remove() Categorie KO "+e);
        }finally {
            session.close();
        }
    }
}
