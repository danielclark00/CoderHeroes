package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Conversations;
import com.bloomtechlabs.coderheroesbea.entities.Profiles;
import com.bloomtechlabs.coderheroesbea.repositories.ConversationsRepository;
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
public class MigrationSeedConversationsTest {
    @Autowired
    ConversationsRepository conversationsRepository;

    @Test
    public void findAllData_thenAllProfilesCorrect() {
        // Given
        List<Conversations> conversations = List.of(
                new Conversations(1, new Profiles(1)),
                new Conversations(2, new Profiles(2)),
                new Conversations(3, new Profiles(3)),
                new Conversations(4, new Profiles(4)),
                new Conversations(5, new Profiles(5)),
                new Conversations(6, new Profiles(6)),
                new Conversations(7, new Profiles(7)),
                new Conversations(8, new Profiles(8))
        );

        // When
        List<Conversations> foundConversations = conversationsRepository.findAll();

        // Then
        assertEquals(conversations.size(), foundConversations.size());
        for (int i = 0; i < conversations.size(); i++) {
            assertTrue(compare(conversations.get(i), foundConversations.get(i)));
        }

    }

    private boolean compare(Conversations conversations, Conversations foundConversations) {
        return conversations.getConversation_id() == foundConversations.getConversation_id()
                && conversations.getProfile().getProfile_id() == foundConversations.getProfile().getProfile_id();
    }
}
