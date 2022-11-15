package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.Instructors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides data exchange with the table 'instructors'
 */
@Repository
public interface InstructorsRepository extends JpaRepository<Instructors, Long> {

}
