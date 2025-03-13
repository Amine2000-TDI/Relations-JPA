package com.ezzahi.dao;

import com.ezzahi.models.Adresse;
import com.ezzahi.models.Adresse;
import com.ezzahi.models.Personne;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AdresseDao implements Idao<Long, Adresse>{
    private EntityManager session;
    private EntityTransaction tx;
    private Logger log = LogManager.getLogger(AdresseDao.class);
    @Override
    public Adresse save(Adresse adresse) {
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            tx.begin();
            adresse = session.merge(adresse);
            tx.commit();
            log.info("Save() Adresse OK "+adresse);
        } catch (Exception e) {
            tx.rollback();
            log.error("Save() Adresse KO "+e);
        }finally {
            session.close();
            return adresse;
        }
    }

    @Override
    public List<Adresse> getAll() {
        List<Adresse> result = null;
        try {
            session = SessionBuilder.getSession().createEntityManager();
            result = session.createQuery("from Adresse a").getResultList();
            log.info("getAll() Adresses OK ");
        } catch (Exception e) {
            log.error("getAll() Adresses KO "+e);
        }finally {
            session.close();
        }
        return result;
    }

    @Override
    public Adresse getById(Long id) {
        Adresse adresse = null;
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            adresse = session.find(Adresse.class,id);
            if(adresse != null){
                log.info("getById() Adresse OK "+adresse);
            }else {
                log.info("getById() OK but no Adresse has found because none withe the id = "+id);
            }
        } catch (Exception e) {
            log.error("getById() Adresse KO "+e);
        }finally {
            session.close();
            return adresse;
        }
    }

    @Override
    public void remove(Long id) {
        try {
            session = SessionBuilder.getSession().createEntityManager();
            tx = session.getTransaction();
            tx.begin();
            Adresse adresse = session.find(Adresse.class,id);
            Personne personne = session.find(Personne.class,adresse.getPersonne().getId());
            if(adresse != null){
                personne.setAdresse(null);
                session.merge(personne);
                session.remove(adresse);
                log.info("Remove() Adresse OK "+adresse);
            }else {
                log.info("Remove() OK but no Adresse has deleted because none withe the id = "+id);
            }
            tx.commit();
        } catch (Exception e) {
            log.error("Remove() Adresse KO "+e);
            tx.rollback();
        }finally {
            session.close();
        }
    }
}
