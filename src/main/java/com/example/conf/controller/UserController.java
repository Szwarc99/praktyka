package com.example.conf.controller;

import com.example.conf.model.Registration;
import com.example.conf.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @GetMapping("/user")
    public User getUser(@RequestParam(value = "firstName",defaultValue = "Piotr") String firstName,
                        @RequestParam(value = "lastName",defaultValue = "Szwarc") String lastName,
                        @RequestParam(value = "age",defaultValue = "21") int age) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);

        return user;
    }

    @PostMapping("/user")
    public User postUser(User user) {
        return user;
    }

}
