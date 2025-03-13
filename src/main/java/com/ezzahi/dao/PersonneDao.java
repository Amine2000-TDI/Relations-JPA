package com.ezzahi.dao;

import com.ezzahi.models.Personne;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PersonneDao implements Idao<Long, Personne>{
    private EntityManager session;
    private EntityTransaction tx;
    private Logger log = LogManager.getLogger(PersonneDao.class);
    @Override
    public Personne save(Personne personne) {
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            tx.begin();
            personne = session.merge(personne);
            tx.commit();
            log.info("Save() Personne OK " + personne);
        } catch (Exception e) {
            tx.rollback();
            log.error("Save() Personne KO " + e);
        } finally {
           session.close();
        }
        return personne;
    }

    @Override
    public List<Personne> getAll() {
        List<Personne> result = null;
        try {
            session = SessionBuilder.getSession().createEntityManager();
            result = session.createQuery("from Personne p").getResultList();
            log.info("getAll() Personnes OK ");
        } catch (Exception e) {
            log.error("getAll() Personnes KO "+e);
        }finally {
            session.close();
            return result;
        }
    }

    @Override
    public Personne getById(Long id) {
        Personne personne = null;
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            personne = session.find(Personne.class,id);
            if(personne != null){
                log.info("getById() Personne OK "+personne);
            }else {
                log.info("getById() OK but no Personne has found because none withe the id = "+id);
            }
        } catch (Exception e) {
            log.error("getById() Personne KO "+e);
        }finally {
            session.close();
            return personne;
        }
    }

    @Override
    public void remove(Long id) {
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            tx.begin();
            Personne personne = session.find(Personne.class,id);
            if(personne != null){
                session.remove(personne);
                log.info("Remove() Personne OK "+personne);
            }else {
                log.info("Remove() OK but no Personne has deleted because none withe the id = "+id);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error("Remove() Personne KO "+e);
        }finally {
            session.close();
        }
    }
}
