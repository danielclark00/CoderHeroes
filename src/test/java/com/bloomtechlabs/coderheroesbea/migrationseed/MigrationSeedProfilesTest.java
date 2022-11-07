package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Profiles;
import com.bloomtechlabs.coderheroesbea.entities.Roles;
import com.bloomtechlabs.coderheroesbea.repositories.ProfilesRepository;
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
public class MigrationSeedProfilesTest {
    @Autowired
    ProfilesRepository profilesRepository;

    @Test
    public void findAllData_thenAllProfilesCorrect() {
        // Given
        List<Profiles> profiles = List.of(
                new Profiles(1, "llama001@maildrop.cc", "Test001 User",
                        "00ulthapbErVUwVJy4x6", new Roles(1),
                        "https://i.stack.imgur.com/frlIf.png", "false"),
                new Profiles(2, "llama002@maildrop.cc", "Test002 User",
                        "00ultwew80Onb2vOT4x6", new Roles(2),
                        "https://i.stack.imgur.com/frlIf.png", "false"),
                new Profiles(3, "llama003@maildrop.cc", "Test003 User",
                        "00ultx74kMUmEW8054x6", new Roles(3),
                        "https://i.stack.imgur.com/frlIf.png", "false"),
                new Profiles(4, "llama004@maildrop.cc", "Test004 User",
                        "00ultwqjtqt4VCcS24x6", new Roles(4),
                        "https://i.stack.imgur.com/frlIf.png", "false"),
                new Profiles(5, "llama005@maildrop.cc", "Test005 User",
                        "00ultwz1n9ORpNFc04x6", new Roles(5),
                        "https://i.stack.imgur.com/frlIf.png", "false"),
                new Profiles(6, "llama006@maildrop.cc", "Test006 User",
                        "00u13omswyZM1xVya4x7", new Roles(1),
                        "https://i.stack.imgur.com/frlIf.png", "false"),
                new Profiles(7, "llama007@maildrop.cc", "Test007 User",
                        "00u13ol5x1kmKxVJU4x7", new Roles(2),
                        "https://i.stack.imgur.com/frlIf.png", "false"),
                new Profiles(8, "llama008@maildrop.cc", "Test008 User",
                        "00u13oned0U8XP8Mb4x7", new Roles(3),
                        "https://i.stack.imgur.com/frlIf.png", "false")
        );

        // When
        List<Profiles> foundProfiles = profilesRepository.findAll();

        // Then
        assertEquals(profiles.size(), foundProfiles.size());
        for (int i = 0; i < profiles.size(); i++) {
            assertTrue(compare(profiles.get(i), foundProfiles.get(i)));
        }
    }
    private boolean compare(Profiles profiles, Profiles foundProfiles) {
        return profiles.getProfile_id()== foundProfiles.getProfile_id()
                && Objects.equals(profiles.getEmail(), foundProfiles.getEmail())
                && Objects.equals(profiles.getName(), foundProfiles.getName())
                && Objects.equals(profiles.getOkta_id(), foundProfiles.getOkta_id())
                && profiles.getRole().getRole_id() == foundProfiles.getRole().getRole_id()
                && Objects.equals(profiles.getAvatarUrl(), foundProfiles.getAvatarUrl())
                && Objects.equals(profiles.getPending(), foundProfiles.getPending());
    }
}
