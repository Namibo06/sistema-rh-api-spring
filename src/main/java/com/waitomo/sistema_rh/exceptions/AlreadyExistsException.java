package com.waitomo.sistema_rh.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(Character sufix,String className){
        super("Est" + sufix + " " + className + " já existe");
    }
}
