package org.example.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.Models.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Transactional
@Repository
public class Repository_Student implements Interface_Student {

    @PersistenceContext
    private final EntityManager entityManager;
    public Repository_Student(EntityManager em) {
        this.entityManager = em;
    }
    @Override
    public Optional<Student> findById(long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }
    @Override
    public Optional<Groups_of_students> findByIdGroup(long id) {
        return Optional.ofNullable(entityManager.find(Groups_of_students.class, id));
    }
    @Override
    public Optional<Courses> findByIdCours(long id) {
        return Optional.ofNullable(entityManager.find(Courses.class, id));
    }
    @Override
    public Optional<Rating> findByIdRating(long id) {
        return Optional.ofNullable(entityManager.find(Rating.class, id));
    }
    @Override
    public List<Student> getAllStudent() {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }
    @Override
    public String getAllStudent_List() {
        List<Student> stud = getAllStudent();
        String Result_Students = "";
        for(Student student: stud) {
            Result_Students += "ID студента - " + student.getId() + ", Фамилия и имя студента: " + student.getName_student() + ", академическая группа: " + student.getGroup_id().getName_group() + "\n";
        }
        return Result_Students;
    }
    @Override
    public List<Courses> getAllCourses() {
        TypedQuery<Courses> query = entityManager.createQuery("select s from Courses s", Courses.class);
        return query.getResultList();
    }

    @Override
    public void getAverage_Rating_Of_The_Group(int average_rating) {
        List<Courses> courses = getAllCourses();
        List<Student> students = getAllStudent();
        String[] name_courses = {"Электромагнетизм", "Приводы", "РЗиАЭ", "Математика", "Станции и подстанции"};

        System.out.println("Средние баллы студентов по курсам группы " + findByIdGroup(average_rating).get().getName_group() + ":");
        for (Courses cor : courses) {
            int count_stud = 0;
            int average = 0;
            for (Student stu : students) {
                average += (float) findById(stu.getId()).stream().filter(s -> s.getGroup_id().getId() == average_rating)
                        .filter(s -> s.getCourses().get(0).equals(cor)).mapToDouble(s -> s.getRating().get(0).getRating()).sum();
                average += (float) findById(stu.getId()).stream().filter(s -> s.getGroup_id().getId() == average_rating)
                        .filter(s -> s.getCourses().get(1).equals(cor)).mapToDouble(s -> s.getRating().get(1).getRating()).sum();

                count_stud += (float) findById(stu.getId()).stream().filter(s -> s.getGroup_id().getId() == average_rating)
                        .filter(s -> s.getCourses().get(0).equals(cor) || s.getCourses().get(1).equals(cor)).count();
            }
            System.out.println(name_courses[(int)(cor.getId() - 1)] + " - " + average / (float) count_stud);
        }
        System.out.println();
    }

}
