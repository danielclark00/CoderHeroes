package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Admins;
import com.bloomtechlabs.coderheroesbea.entities.Instructors;
import com.bloomtechlabs.coderheroesbea.entities.Profiles;
import com.bloomtechlabs.coderheroesbea.entities.Programs;
import com.bloomtechlabs.coderheroesbea.repositories.InstructorsRepository;
import com.bloomtechlabs.coderheroesbea.repositories.ProgramsRepository;
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
public class MigrationSeedProgramsTest {
    @Autowired
    ProgramsRepository programsRepository;

    @Test
    public void findAllData_thenAllProgramsCorrect() {
        // Given
        List<Programs> programs = List.of(
                new Programs(1, "Codercamp",
                        "Students build their own app based on their own interest"),
                new Programs(2, "Codersitters",
                        "Coding through play, coding + babysitting (not just code, also creativity)"),
                new Programs(3, "Coderyoga",
                        "Kids learn coding basics through yoga stories and exercise")
        );

        // When
        List<Programs> foundPrograms = programsRepository.findAll();

        // Then
        assertEquals(programs.size(), foundPrograms.size());
        for (int i = 0; i < programs.size(); i++) {
            assertTrue(compare(programs.get(i), foundPrograms.get(i)));
        }

    }

    private boolean compare(Programs programs, Programs foundPrograms) {

        return programs.getProgram_id() == foundPrograms.getProgram_id()
                && Objects.equals(programs.getProgram_name(), foundPrograms.getProgram_name())
                && Objects.equals(programs.getProgram_description(), foundPrograms.getProgram_description())
                ;
    }
}
