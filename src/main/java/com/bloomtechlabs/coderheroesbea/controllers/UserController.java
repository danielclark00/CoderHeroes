package com.bloomtechlabs.coderheroesbea.controllers;

import com.bloomtechlabs.coderheroesbea.models.User;
import com.bloomtechlabs.coderheroesbea.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.PresentationDirection;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    
    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> find(@PathVariable("id") String id) {
        Optional<User> user = service.find(id);
        return ResponseEntity.of(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User newUser = service.create(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(newUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") String id,
                                     @Valid @RequestBody User updatedUser) {
        Optional<User> updated = service.update(id, updatedUser);


        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    User newUser = service.create(updatedUser);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(newUser.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(newUser);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        return ResponseEntity.badRequest().body(map);
    }

}
