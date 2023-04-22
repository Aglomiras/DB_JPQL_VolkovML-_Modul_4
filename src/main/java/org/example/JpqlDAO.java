package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.Repository.Interface_Student;
import org.example.Repository.Repository_Student;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class JpqlDAO {
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(JpqlDAO.class, args);

        EntityManagerFactory factory = applicationContext.getBean(EntityManagerFactory.class);
        EntityManager entityManager = factory.createEntityManager();
        Interface_Student stuInRep = new Repository_Student(entityManager);

        /**
         * Магия начинается*/
        //Используем метод, который вернет нам Optional для первого студента. То есть, будет получена вся информация об этом студенте
        System.out.println(stuInRep.findById(1));
        //Можно вернуть список
        System.out.println(stuInRep.findById(1).get());
        //Используем метод, чтобы узнать имя студента
        System.out.println(stuInRep.findById(1).get().getName_student());
        //Используем метод, чтобы узнать список всех его оценок
        System.out.println(stuInRep.findById(1).get().getRating());
        //Используем метод, чтобы узнать 1 и 2 его оценку
        System.out.println(stuInRep.findById(1).get().getRating().get(0));
        System.out.println(stuInRep.findById(1).get().getRating().get(1));
        //Используем метод, чтобы узнать количество курсов студента
        System.out.println(stuInRep.findById(1).get().getCourses().toArray().length);

        stuInRep.getAverage_Rating_Of_The_Group(1);
        stuInRep.getAverage_Rating_Of_The_Group(2);
        stuInRep.getAverage_Rating_Of_The_Group(3);
        stuInRep.getAverage_Rating_Of_The_Group(4);
        stuInRep.getAverage_Rating_Of_The_Group(5);

        Console.main(args);
    }
}