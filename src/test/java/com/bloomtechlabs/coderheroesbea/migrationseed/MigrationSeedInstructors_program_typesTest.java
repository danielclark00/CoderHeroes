package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Instructors;
import com.bloomtechlabs.coderheroesbea.entities.Instructors_program_types;
import com.bloomtechlabs.coderheroesbea.entities.Programs;
import com.bloomtechlabs.coderheroesbea.repositories.Instructors_program_typesRepository;
import com.bloomtechlabs.coderheroesbea.repositories.ProgramsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MigrationSeedInstructors_program_typesTest {
    @Autowired
    Instructors_program_typesRepository instructors_program_typesRepository;

    @Test
    public void findAllData_thenAllInstructors_program_typesCorrect() {
        // Given
        List<Instructors_program_types> instructors_program_types = List.of(
                new Instructors_program_types(1, new Instructors(1),
                        new Programs(1)),
                new Instructors_program_types(2, new Instructors(1),
                        new Programs(2)),
                new Instructors_program_types(3, new Instructors(2),
                        new Programs(3))
        );

        // When
        List<Instructors_program_types> foundInstructors_program_types = instructors_program_typesRepository.findAll();

        // Then
        assertEquals(instructors_program_types.size(), foundInstructors_program_types.size());
        for (int i = 0; i < instructors_program_types.size(); i++) {
            assertTrue(compare(instructors_program_types.get(i), foundInstructors_program_types.get(i)));
        }

    }

    private boolean compare(Instructors_program_types instructors_program_types,
                            Instructors_program_types foundInstructors_program_types) {

        return instructors_program_types.getInstructors_program_types_id()
                    == foundInstructors_program_types.getInstructors_program_types_id()
                && instructors_program_types.getInstructor().getInstructor_id()
                    == foundInstructors_program_types.getInstructor().getInstructor_id()
                && instructors_program_types.getPrograms().getProgram_id()
                    == foundInstructors_program_types.getPrograms().getProgram_id()
                ;
    }
}
