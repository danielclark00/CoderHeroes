package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.FeedbackBadges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackBadgesRepository extends JpaRepository<FeedbackBadges, Long> {

}
