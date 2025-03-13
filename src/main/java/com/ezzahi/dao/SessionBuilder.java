package com.ezzahi.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessionBuilder {
    private static final Logger log = LogManager.getLogger(SessionBuilder.class);
    private static EntityManagerFactory session;
    private SessionBuilder(){}
    public static EntityManagerFactory getSession(){
        if (session == null){
            try {
                session = Persistence.createEntityManagerFactory("mydb");
            }catch (Exception e){
                log.error("erreur dans la création de l'entityManagerFactory : "+e);
            }
        }
        return session;
    }
}
