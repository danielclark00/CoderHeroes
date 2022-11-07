package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.Conversations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides data exchange with the table 'conversations'
 */
@Repository
public interface ConversationsRepository extends JpaRepository<Conversations, Long> {

}
