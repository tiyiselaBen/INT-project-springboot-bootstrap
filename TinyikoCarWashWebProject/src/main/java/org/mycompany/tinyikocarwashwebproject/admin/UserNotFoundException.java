package org.mycompany.tinyikocarwashwebproject.admin;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
