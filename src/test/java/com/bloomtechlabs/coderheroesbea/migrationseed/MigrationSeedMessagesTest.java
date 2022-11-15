package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Conversations;
import com.bloomtechlabs.coderheroesbea.entities.Messages;
import com.bloomtechlabs.coderheroesbea.entities.Profiles;
import com.bloomtechlabs.coderheroesbea.repositories.MessagesRepository;
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
public class MigrationSeedMessagesTest {
    @Autowired
    MessagesRepository messagesRepository;

    @Test
    public void findAllData_thenAllProfilesCorrect() {
        // Given
        List<Messages> messages = List.of(
                new Messages(1, new Date(), "Help with Homework?", true,
                        "I need the answers to the assignment please.", new Profiles(1),
                        new Conversations(7)),
                new Messages(2, new Date(), "What's my grade?", true,
                        "Hey Ms. Teacher can you tell me my grade?", new Profiles(5),
                        new Conversations(7)),
                new Messages(3, new Date(), "When is class?", false,
                        "I noticed the time was funky and had to ask.", new Profiles(4),
                        new Conversations(8)),
                new Messages(4, new Date(), "Is this a yoga course?", false,
                        "How is yoga and coding taught together?", new Profiles(1),
                        new Conversations(4)),
                new Messages(5, new Date(), "Where is my achievement?", false,
                        "my achievement didn't pop up when I did course.", new Profiles(8),
                        new Conversations(5))
        );

        // When
        List<Messages> foundMessages = messagesRepository.findAll();

        // Then
        assertEquals(messages.size(), foundMessages.size());
        for (int i = 0; i < messages.size(); i++) {
            assertTrue(compare(messages.get(i), foundMessages.get(i)));
        }

    }

    private boolean compare(Messages messages, Messages foundMessages) {
        return messages.getMessages_id() == foundMessages.getMessages_id()
                && messages.getRead() == foundMessages.getRead()
                && Objects.equals(messages.getTitle(), foundMessages.getTitle())
                && Objects.equals(messages.getMessage(), foundMessages.getMessage())
                && messages.getProfile().getProfile_id() == foundMessages.getProfile().getProfile_id()
                && messages.getConversation().getConversation_id()
                == foundMessages.getConversation().getConversation_id();
    }

}
