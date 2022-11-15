package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Parents;
import com.bloomtechlabs.coderheroesbea.entities.Profiles;
import com.bloomtechlabs.coderheroesbea.repositories.ParentsRepository;
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
public class MigrationSeedParentsTest {
    @Autowired
    ParentsRepository parentsRepository;

    @Test
    public void findAllData_thenAllParentsCorrect() {
        // Given
        List<Parents> parents = List.of(
                new Parents(1, new Profiles(4))
        );

        // When
        List<Parents> foundParents = parentsRepository.findAll();

        // Then
        assertEquals(parents.size(), foundParents.size());
        for (int i = 0; i < parents.size(); i++) {
            assertTrue(compare(parents.get(i), foundParents.get(i)));
        }

    }

    private boolean compare(Parents parents, Parents foundParents) {
        return parents.getParent_id() == foundParents.getParent_id()
                && parents.getProfile().getProfile_id() == foundParents.getProfile().getProfile_id();
    }
}
