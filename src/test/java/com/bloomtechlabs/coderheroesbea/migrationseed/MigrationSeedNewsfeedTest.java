package com.bloomtechlabs.coderheroesbea.migrationseed;

import com.bloomtechlabs.coderheroesbea.entities.Admins;
import com.bloomtechlabs.coderheroesbea.entities.Newsfeed;
import com.bloomtechlabs.coderheroesbea.entities.Profiles;
import com.bloomtechlabs.coderheroesbea.repositories.AdminsRepository;
import com.bloomtechlabs.coderheroesbea.repositories.NewsfeedRepository;
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
public class MigrationSeedNewsfeedTest {
    @Autowired
    NewsfeedRepository newsfeedRepository;

    @Test
    public void findAllData_thenAllAdminsCorrect() {
        // Given
        List<Newsfeed> newsfeeds = List.of(
                new Newsfeed(1, "Check out these coding camps!", "https://www.idtech.com/",
                        "This is great way to learn more about the coding world!", new Date()),
                new Newsfeed(2, "Always check the docs!", "https://developer.mozilla.org/en-US/",
                        "Make sure to check the documentations if you are looking for a method to use and are not sure what the precise name is!",
                        new Date()),
                new Newsfeed(3, "Practice your algorithms!", "https://leetcode.com/",
                    "Sign up and test your problem-solving skills!", new Date()),
                new Newsfeed(4, "Robot dog learns to walk in one hour", "https://www.sciencedaily.com/releases/2022/07/220718122229.htm",
                        "Like a newborn animal, a four-legged robot stumbles around during its first walking attempts. But while a foal or a giraffe needs much longer to master walking, the robot learns to move forward fluently in just one hour. A computer program acts as the artificial presentation of the animal's spinal cord, and learns to optimize the robot's movement in a short time. The artificial neural network is not yet ideally adjusted at the beginning, but rapidly self-adjusts.",
                        new Date())
        );

        // When
        List<Newsfeed> foundNewsfeeds = newsfeedRepository.findAll();

        // Then
        assertEquals(newsfeeds.size(), foundNewsfeeds.size());
        for (int i = 0; i < newsfeeds.size(); i++) {
            assertTrue(compare(newsfeeds.get(i), foundNewsfeeds.get(i)));
        }

    }

    private boolean compare(Newsfeed newsfeeds, Newsfeed foundNewsfeeds) {
        return newsfeeds.getNewsfeed_id() == foundNewsfeeds.getNewsfeed_id()
                && Objects.equals(newsfeeds.getTitle(), foundNewsfeeds.getTitle())
                && Objects.equals(newsfeeds.getLink(), foundNewsfeeds.getLink())
                && Objects.equals(newsfeeds.getDescription(), foundNewsfeeds.getDescription())
                ;
    }
}
