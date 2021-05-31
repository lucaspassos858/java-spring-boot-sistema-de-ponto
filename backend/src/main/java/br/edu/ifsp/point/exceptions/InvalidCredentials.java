package br.edu.ifsp.point.exceptions;

public class InvalidCredentials extends RuntimeException{

    public InvalidCredentials(String message){
        super(message);
    }

}
