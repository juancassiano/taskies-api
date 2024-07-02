package com.github.juancassiano.taskies.domain.exception;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String username) {
        super(String.format("Username %s not found", username));
    }
}
