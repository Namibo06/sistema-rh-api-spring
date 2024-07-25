package com.waitomo.sistema_rh.exceptions;

public class TokenNullOrEmptyException extends RuntimeException{
    public TokenNullOrEmptyException(){
        super("Token nulo ou vazio");
    }
}
