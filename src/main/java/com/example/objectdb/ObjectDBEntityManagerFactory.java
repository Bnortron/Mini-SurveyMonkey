package com.example.objectdb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ObjectDBEntityManagerFactory {
    private static final EntityManagerFactory emf = ObjectDBConfiguration.getEntityManagerFactory();
    private final String databaseUrl;

    public ObjectDBEntityManagerFactory(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
