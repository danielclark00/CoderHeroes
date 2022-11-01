package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Provides data exchange with the table 'messages'
 */
@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
}
