package com.ezzahi.dao;

import com.ezzahi.models.Personne;
import com.ezzahi.models.Projet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ProjetDao implements Idao<Long, Projet> {
    private EntityManager session;
    private EntityTransaction tx;
    private Logger log = LogManager.getLogger(ProjetDao.class);
    @Override
    public Projet save(Projet projet) {
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            tx.begin();
            projet = session.merge(projet);
            tx.commit();
            log.info("Save() projet OK "+projet);
        } catch (Exception e) {
            tx.rollback();
            log.error("Save() projet KO "+e);
        }finally {
            session.close();
            return projet;
        }
    }

    @Override
    public List<Projet> getAll() {
        List<Projet> result = null;
        try {
            session = SessionBuilder.getSession().createEntityManager();
            result = session.createQuery("from Projet p").getResultList();
            log.info("getAll() projet OK ");
        } catch (Exception e) {
            log.error("getAll() projet KO "+e);
        }finally {
            session.close();
            return result;
        }
    }

    @Override
    public Projet getById(Long id) {
        Projet projet = null;
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            projet = session.find(Projet.class,id);
            if(projet != null){
                log.info("getById() Projet OK "+projet);
            }else {
                log.info("getById() OK but no projet has found because none withe the id = "+id);
            }
        } catch (Exception e) {
            log.error("getById() Projet KO "+e);
        }finally {
            session.close();
            return projet;
        }
    }

    @Override
    public void remove(Long id) {
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            tx.begin();
            Projet projet = session.find(Projet.class,id);
            if(projet != null){
                session.remove(projet);
                log.info("Remove() Projet OK "+projet);
            }else {
                log.info("Remove() OK but no Projet has deleted because none withe the id = "+id);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("Remove() Projet KO "+e);
        }finally {
            session.close();
        }
    }
}
