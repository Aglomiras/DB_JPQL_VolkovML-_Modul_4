package org.example.Repository;

import org.example.Models.*;

import java.util.List;
import java.util.Optional;

public interface Interface_Student {
    Optional<Student> findById(long id);

    Optional<Groups_of_students> findByIdGroup(long id);

    Optional<Courses> findByIdCours(long id);

    Optional<Rating> findByIdRating(long id);

    List<Student> getAllStudent();

    String getAllStudent_List();

    List<Courses> getAllCourses();

    void getAverage_Rating_Of_The_Group(int average_rating);

}
