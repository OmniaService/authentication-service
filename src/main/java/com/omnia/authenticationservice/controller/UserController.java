package com.omnia.authenticationservice.controller;

import com.omnia.authenticationservice.model.Message;
import com.omnia.authenticationservice.model.UserEntity;
import com.omnia.authenticationservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RefreshScope
@RestController
@RequestMapping(value = "/auth", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public Message save(@RequestBody UserEntity user) {
        log.debug("Attempting to save user: " + user);
        UserEntity savedUser = userService.saveUser(user);
        log.debug("User saved: " + savedUser);
        return new Message("User created successfully!");
    }

    @GetMapping("/")
    public List<UserEntity> getAll() {
        log.debug("Fetching all users...");
        return new ArrayList<>(userService.findAll());
    }

}
