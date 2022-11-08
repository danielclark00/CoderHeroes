package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Children;
import com.bloomtechlabs.coderheroesbea.entities.Parents;
import com.bloomtechlabs.coderheroesbea.entities.Profiles;
import com.bloomtechlabs.coderheroesbea.repositories.ChildrenRepository;
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
public class MigrationSeedChildrenTest {
    @Autowired
    ChildrenRepository childrenRepository;

    @Test
    public void findAllData_thenAllChildrenCorrect() {
        // Given
        List<Children> children = List.of(
                new Children(1, new Profiles(5), "ILoveFortnite", 8, new Parents(1))
        );

        // When
        List<Children> foundChildren = childrenRepository.findAll();

        // Then
        assertEquals(children.size(), foundChildren.size());
        for (int i = 0; i < children.size(); i++) {
            assertTrue(compare(children.get(i), foundChildren.get(i)));
        }

    }

    private boolean compare(Children children, Children foundChildren) {
        return children.getChild_id() == foundChildren.getChild_id()
                && children.getProfile().getProfile_id() == foundChildren.getProfile().getProfile_id()
                && Objects.equals(children.getUsername(), foundChildren.getUsername())
                && children.getAge() == foundChildren.getAge()
                && children.getParents().getParent_id() == foundChildren.getParents().getParent_id();
    }
}
