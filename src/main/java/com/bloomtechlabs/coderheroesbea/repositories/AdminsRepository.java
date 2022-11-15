package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides data exchange with the table 'admins'
 */
@Repository
public interface AdminsRepository extends JpaRepository<Admins, Long> {

}


