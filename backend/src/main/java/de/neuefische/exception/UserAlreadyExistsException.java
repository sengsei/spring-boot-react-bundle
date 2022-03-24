package de.neuefische.exception;

public class UserAlreadyExistsException extends IllegalStateException{

    UserAlreadyExistsException() {
        super("user already exists");
    }
}
