package de.neuefische.exception;

public class PasswordNotMatchException extends IllegalStateException {

    PasswordNotMatchException() {
        super("passwords do not match");
    }
}
