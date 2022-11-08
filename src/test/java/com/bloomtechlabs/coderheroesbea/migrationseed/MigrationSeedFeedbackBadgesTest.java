package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.FeedbackBadges;
import com.bloomtechlabs.coderheroesbea.repositories.FeedbackBadgesRepository;
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
public class MigrationSeedFeedbackBadgesTest {
    @Autowired
    FeedbackBadgesRepository feedbackBadgesRepository;

    @Test
    public void findAllData_thenAllfoundFeedbackBadgesCorrect() {
        // Given
        List<FeedbackBadges> feedbackBadges = List.of(
                new FeedbackBadges(1, "Bright Idea!", "brightidea"),
                new FeedbackBadges(2, "Caring Helper!", "caringhelper"),
                new FeedbackBadges(3, "Cool Coder!", "coolcoder"),
                new FeedbackBadges(4, "Daily Champion!", "dailychampion"),
                new FeedbackBadges(5, "Designer!", "designer"),
                new FeedbackBadges(6, "Organized!", "organized"),
                new FeedbackBadges(7, "Personal Growth!", "personalgrowth"),
                new FeedbackBadges(8, "Team Player!", "teamplayer")
        );

        // When
        List<FeedbackBadges> foundFeedbackBadges = feedbackBadgesRepository.findAll();

        // Then
        assertEquals(feedbackBadges.size(), foundFeedbackBadges.size());
        for (int i = 0; i < feedbackBadges.size(); i++) {
            assertTrue(compare(feedbackBadges.get(i), foundFeedbackBadges.get(i)));
        }

    }

    private boolean compare(FeedbackBadges feedbackBadges, FeedbackBadges foundFeedbackBadges) {
        return feedbackBadges.getBadge_id() == foundFeedbackBadges.getBadge_id()
                && Objects.equals(feedbackBadges.getName(), foundFeedbackBadges.getName())
                && Objects.equals(feedbackBadges.getImage(), foundFeedbackBadges.getImage());
    }
}
