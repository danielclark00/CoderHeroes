package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.Programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides data exchange with the table 'programs'
 */
@Repository
public interface ProgramsRepository extends JpaRepository<Programs, Long> {

}
