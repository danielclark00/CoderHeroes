package com.bloomtechlabs.coderheroesbea.services;

import com.bloomtechlabs.coderheroesbea.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryUserRepository extends CrudRepository<User, String> {}
