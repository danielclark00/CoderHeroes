package com.bloomtechlabs.coderheroesbea.repositories;

import com.bloomtechlabs.coderheroesbea.entities.StudentBadges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentBadgesRepository extends JpaRepository<StudentBadges, Long> {

}
