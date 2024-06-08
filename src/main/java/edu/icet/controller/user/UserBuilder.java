package edu.icet.controller.user;

import edu.icet.dto.User;
import edu.icet.util.UserType;

import java.time.LocalDate;

public class UserBuilder {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;

    private UserType type;
    private String email;
    private String password;

    public UserBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setType(UserType type){
        this.type = type;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public User getUser(){
        return new User(id,firstName,lastName,dob,address,type,email,password);
    }
}
