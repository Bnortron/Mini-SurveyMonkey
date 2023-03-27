package com.example;

import com.example.objectdb.ObjectDBEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MiniSurveyMonkeyApplication {

    @Bean
    public ObjectDBEntityManagerFactory entityManagerFactory() {
        return new ObjectDBEntityManagerFactory("$objectdb/db/survey.odb");
    }

    public static void main(String[] args) {
        SpringApplication.run(MiniSurveyMonkeyApplication.class, args);
    }
}
