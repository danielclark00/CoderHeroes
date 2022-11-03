package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Admins;
import com.bloomtechlabs.coderheroesbea.entities.Profiles;
import com.bloomtechlabs.coderheroesbea.repositories.AdminsRepository;
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
public class MigrationSeedAdminsTest {
    @Autowired
    AdminsRepository adminsRepository;

    @Test
    public void findAllData_thenAllAdminsCorrect() {
        // Given
        List<Admins> admins = List.of(
                new Admins(1, new Profiles(7))
        );

        // When
        List<Admins> foundAdmins = adminsRepository.findAll();

        // Then
        assertEquals(admins.size(), foundAdmins.size());
        for (int i = 0; i < admins.size(); i++) {
            assertTrue(compare(admins.get(i), foundAdmins.get(i)));
        }

    }

    private boolean compare(Admins admins, Admins foundAdmins) {
        return admins.getAdmin_id() == foundAdmins.getAdmin_id()
                && admins.getProfile().getProfile_id() == foundAdmins.getProfile().getProfile_id();
    }
}
