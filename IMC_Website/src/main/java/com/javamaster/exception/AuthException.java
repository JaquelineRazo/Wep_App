package com.javamaster.exception;

public class AuthException extends Exception{
    
    private String message;
    
    public AuthException(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
