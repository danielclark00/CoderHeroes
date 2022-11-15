package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.*;
import com.bloomtechlabs.coderheroesbea.repositories.StudentBadgesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MigrationSeedStudentBadgesTest {
    @Autowired
    StudentBadgesRepository studentBadgesRepository;

    @Test
    public void findAllData_thenAllStudentBadgesCorrect() {
        // Given
        List<StudentBadges> studentBadges = List.of(
                new StudentBadges(1, new Children(1), new FeedbackBadges(1)),
                new StudentBadges(2, new Children(1), new FeedbackBadges(3))
        );

        // When
        List<StudentBadges> foundStudentBadges = studentBadgesRepository.findAll();

        // Then
        assertEquals(studentBadges.size(), foundStudentBadges.size());
        for (int i = 0; i < studentBadges.size(); i++) {
            assertTrue(compare(studentBadges.get(i), foundStudentBadges.get(i)));
        }

    }

    private boolean compare(StudentBadges studentBadges, StudentBadges foundStudentBadges) {
        return studentBadges.getStudent_badge_id() == foundStudentBadges.getStudent_badge_id()
                && studentBadges.getChildren().getChild_id() == foundStudentBadges.getChildren().getChild_id()
                && studentBadges.getFeedbackBadges().getBadge_id() == foundStudentBadges.getFeedbackBadges().getBadge_id();
    }
}
