package com.waitomo.sistema_rh.exceptions;

import com.waitomo.sistema_rh.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException ex){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(),"Registro não encontrado");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException ex){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), "Erro na requisição");
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlreadyExistsException(AlreadyExistsException ex){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage(), "Registro já existente");
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }
}
