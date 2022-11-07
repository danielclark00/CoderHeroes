package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.Instructors_program_types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides data exchange with the table 'instructors_program_type'
 */
@Repository
public interface Instructors_program_typesRepository extends JpaRepository<Instructors_program_types, Long> {
}
