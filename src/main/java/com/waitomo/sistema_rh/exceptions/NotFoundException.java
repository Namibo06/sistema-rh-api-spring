package com.waitomo.sistema_rh.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String className,Character sufix){
        super(className+ " não encontrad" +sufix);
    }
}
