package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Provides data exchange with the table 'roles'
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
