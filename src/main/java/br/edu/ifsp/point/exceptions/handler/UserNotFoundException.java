package br.edu.ifsp.point.exceptions.handler;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

}
