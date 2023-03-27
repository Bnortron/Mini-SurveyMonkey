package com.example.objectdb;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBConfiguration {
    private static final String DB_NAME = "MiniSurveyMonkey.odb";
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory(DB_NAME);
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
