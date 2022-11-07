package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.*;
import com.bloomtechlabs.coderheroesbea.repositories.InstructorsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MigrationSeedInstructorsTest {
    @Autowired
    InstructorsRepository instructorsRepository;

    @Test
    public void findAllData_thenAllInstructorsCorrect() {
        // Given
        List<Instructors> instructors = List.of(
                new Instructors(1, "Brianne Caplan", 2, null,
                        "I love spaghetti and code, but not the two together.", new Profiles(3), "false",
                        new Date(), new Admins(1)),
                new Instructors(2, "Adam Smith", 5, null,
                        "Coding is life.", new Profiles(8), "true", new Date(), new Admins(1))
        );

        // When
        List<Instructors> foundInstructors = instructorsRepository.findAll();

        // Then
        assertEquals(instructors.size(), foundInstructors.size());
        for (int i = 0; i < instructors.size(); i++) {
            assertTrue(compare(instructors.get(i), foundInstructors.get(i)));
        }

    }

    private boolean compare(Instructors instructors, Instructors foundInstructors) {

        return instructors.getInstructor_id() == foundInstructors.getInstructor_id()
                && Objects.equals(instructors.getInstructor_name(), foundInstructors.getInstructor_name())
                && instructors.getRating() == foundInstructors.getRating()
                && Objects.equals(instructors.getBio(), foundInstructors.getBio())
                && instructors.getProfile().getProfile_id() == foundInstructors.getProfile().getProfile_id()
                && Objects.equals(instructors.getStatus(), foundInstructors.getStatus())
                && instructors.getAdmin().getAdmin_id() == foundInstructors.getAdmin().getAdmin_id()
                ;
    }
}
