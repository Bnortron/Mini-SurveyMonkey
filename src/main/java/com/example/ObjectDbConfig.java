package com.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableTransactionManagement
public class ObjectDbConfig {
    @Bean
    @DependsOn("jpaVendorAdapter")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example");
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setJpaPropertyMap(Collections.singletonMap("javax.persistence.jdbc.url", "objectdb:db/test.odb"));
        return em;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build(); // You can replace H2 with any other database that ObjectDB supports
    }

    @Bean
    public ObjectDbJpaVendorAdapter jpaVendorAdapter() {
        return new ObjectDbJpaVendorAdapter();
    }
}
