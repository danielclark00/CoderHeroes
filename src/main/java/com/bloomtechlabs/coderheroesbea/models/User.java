package com.bloomtechlabs.coderheroesbea.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.awt.*;


public class User {

    @NotNull(message = "name is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "name must be a string")
    private String username;


    private String id;

    @NotNull(message = "email is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "name must be a string")
    private String email;

    @NotNull(message = "profile picture is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "name must be a string")
    private String profileImage;

    public User(String username, String id, String email, String profileImage) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileImage() {
        return profileImage;
    }
    public User updateWith(User user) {
        return new User(
                this.id,
                user.username,
                user.email,
                user.profileImage
        );
    }
}
