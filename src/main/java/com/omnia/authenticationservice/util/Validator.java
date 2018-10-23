package com.omnia.authenticationservice.util;


import com.omnia.authenticationservice.exceptions.IncorrectValuesEntered;
import com.omnia.authenticationservice.model.UserEntity;

public class Validator {

    private Validator() {

    }

    public static UserEntity validateUser(UserEntity user) {
        checkUsername(user.getUsername());
        return user;
    }

    private static void checkUsername(String username) {
        if (username.length() < 6 || username.length() > 20)
            throw new IncorrectValuesEntered("username has to be between 6-20 characters");
        if (!username.matches("[0-9A-z-_(){}.]+")) {
            throw new IncorrectValuesEntered("username can only contain a-Z 0-9 -_(){}.");
        }

    }
}
