package com.example;

import com.objectdb.jpa.Provider;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceProvider;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.ProviderUtil;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;

import java.util.Map;

public class ObjectDbJpaVendorAdapter extends AbstractJpaVendorAdapter {
    @Override
    public PersistenceProvider getPersistenceProvider() {
        return new PersistenceProvider() {
            @Override
            public EntityManagerFactory createEntityManagerFactory(String emName, Map map) {
                return (EntityManagerFactory) new Provider().createEntityManagerFactory(emName, map);
            }

            @Override
            public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) {
                return null;
            }

            @Override
            public void generateSchema(PersistenceUnitInfo info, Map map) {

            }

            @Override
            public boolean generateSchema(String persistenceUnitName, java.util.Map map) {
                return new Provider().generateSchema(persistenceUnitName, map);
            }

            @Override
            public ProviderUtil getProviderUtil() {
                return null;
            }
        };
    }
}
