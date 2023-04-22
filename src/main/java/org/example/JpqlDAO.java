package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.Repository.Interface_Student;
import org.example.Repository.Repository_Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class OrmDemoApplication {
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(OrmDemoApplication.class, args);

        EntityManagerFactory factory = applicationContext.getBean(EntityManagerFactory.class);
        EntityManager entityManager = factory.createEntityManager();
        Interface_Student stuInRep = new Repository_Student(entityManager);

        stuInRep.getAllStudent();
    }
}