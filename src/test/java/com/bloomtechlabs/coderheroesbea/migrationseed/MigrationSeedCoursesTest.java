package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.*;
import com.bloomtechlabs.coderheroesbea.repositories.CoursesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MigrationSeedCoursesTest {
    @Autowired
    CoursesRepository coursesRepository;

    @Test
    public void findAllData_thenAllCoursesCorrect() {
        // Given
        List<Courses> courses = List.of(
                new Courses(1, "App Building Fundamentals",
                        "A month-long course where students with design, build, and deploy an app from beginning to end!",
                        new Date(), new String[]{"Monday"},  20, 0, 7, 12,
                        new Instructors(1), new Programs(1),  new Time(8,0,0), new Time(12,30,0),
                        new Date (2022,4,4), new Date(2022,4,28), 4, "Easy",
                        "Group", "https://docs.google.com"),
                new Courses(2, "Mindful Design",
                        "Students will learn about creativity and web design basics",
                        new Date(), new String[]{"Wednesday", "Friday"},  12, 0, 6, 10,
                        new Instructors(2), new Programs(3),  new Time(15,30,0), new Time(17,45,0),
                        new Date (2022,4,4), new Date(2022,4,28), 8, "Medium",
                        "1-on-1", "https://docs.google.com")
        );


        // When
        List<Courses> foundCourses = coursesRepository.findAll();

        // Then
        assertEquals(courses.size(), foundCourses.size());
        for (int i = 0; i < courses.size(); i++) {
            assertTrue(compare(courses.get(i), foundCourses.get(i)));
        }

    }

    private boolean compare(Courses courses, Courses foundCourses) {
        if (courses.getDays_of_week().length != courses.getDays_of_week().length) {
            return false;
        }
        for (int i = 0; i < courses.getDays_of_week().length; i++) {
            if (!Objects.equals(courses.getDays_of_week()[i], foundCourses.getDays_of_week()[i])) {
                return false;
            }
        }

        return courses.getCourse_id() == foundCourses.getCourse_id()
                && Objects.equals(courses.getCourse_name(), foundCourses.getCourse_name())
                && Objects.equals(courses.getCourse_description(), foundCourses.getCourse_description())
                && courses.getMax_size() == foundCourses.getMax_size()
                && courses.getEnrolled_students() == foundCourses.getEnrolled_students()
                && courses.getMin_age() == foundCourses.getMin_age()
                && courses.getMax_age() == foundCourses.getMax_age()
                && courses.getInstructor().getInstructor_id() == foundCourses.getInstructor().getInstructor_id()
                && courses.getProgram().getProgram_id() == foundCourses.getProgram().getProgram_id()
                && courses.getNumber_of_sessions() == foundCourses.getNumber_of_sessions()
                && Objects.equals(courses.getDifficulty(), foundCourses.getDifficulty())
                && Objects.equals(courses.getSession_type(), foundCourses.getSession_type())
                && Objects.equals(courses.getSyllabus_link(), foundCourses.getSyllabus_link())
                ;
    }
}
