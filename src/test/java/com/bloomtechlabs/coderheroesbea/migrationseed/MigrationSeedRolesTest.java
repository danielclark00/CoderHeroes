package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Roles;
import com.bloomtechlabs.coderheroesbea.repositories.RolesRepository;
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
public class MigrationSeedRolesTest {
    @Autowired
    private RolesRepository rolesRepository;

    @Test
    public void findAllData_thenAllRolesCorrect() {
        // Given
        List<Roles> roles = List.of(
                new Roles(1, "super_admin"),
                new Roles(2, "admin"),
                new Roles(3, "instructor"),
                new Roles(4, "parent"),
                new Roles(5, "child")
        );

        // When
        List<Roles> foundRoles = rolesRepository.findAll();

        //Then
        assertEquals(roles.size(), foundRoles.size());
        for (int i = 0; i < roles.size(); i++) {
            assertTrue(compare(roles.get(i), foundRoles.get(i)));
        }

    }

    private boolean compare(Roles roles, Roles foundRoles) {
        return roles.getRole_id() == foundRoles.getRole_id()
                && Objects.equals(roles.getRole_name(), foundRoles.getRole_name());
    }
}
