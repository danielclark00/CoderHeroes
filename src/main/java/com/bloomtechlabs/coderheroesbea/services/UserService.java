package com.bloomtechlabs.coderheroesbea.services;

import com.bloomtechlabs.coderheroesbea.models.User;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@EnableMapRepositories
public class UserService {
    private final CrudRepository<User, String> repository;

    public UserService(CrudRepository<User, String> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultUsers());
    }

    private static List<User> defaultUsers() {
        return List.of(
                new User("User1",UUID.randomUUID().toString(),"User1@email.com","https://randomuser.me/api/portraits/men/77.jpg"),
                new User("User2",UUID.randomUUID().toString(), "User2@email.com","https://randomuser.me/api/portraits/women/7.jpg"),
                new User("User3",UUID.randomUUID().toString(),"User3@email.com","https://randomuser.me/api/portraits/lego/3.jpg")
        );
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.findAll();
        users.forEach(list::add);
        return list;
    }

    public Optional<User> find(String id) { return repository.findById(id); }

    public User create(User user) {
        // To ensure the user ID remains unique,
        // use the UUID random ID generator.
        User copy = new User(
                user.getUsername(),
                UUID.randomUUID().toString(),
                user.getEmail(),
                user.getProfileImage()
        );
        return repository.save(copy);
    }

    public Optional<User> update(String id, User newUser) {
        // Only update a User if they can be found first.
        return repository.findById(id)
                .map(oldUser -> {
                    User updated = oldUser.updateWith(newUser);
                    return repository.save(updated);
                });
    }

    public void delete(String id) { repository.deleteById(id); }
}
