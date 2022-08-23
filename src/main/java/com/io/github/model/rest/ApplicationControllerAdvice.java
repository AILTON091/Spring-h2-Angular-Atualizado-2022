package com.io.github.model.rest;

import com.io.github.model.exeption.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

// @RestControler todo erro afeta todo endpoint
@RestControllerAdvice
public class ApplicationControllerAdvice {

    //Metodo que faz a captura dos erros e coloca na classe Exception os erros
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handlerValidationErros(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorList = bindingResult.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErros(errorList);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex){
        String mensagemErro= ex.getMessage();
        HttpStatus codigoStatus = ex.getStatus();
        ApiErros apiErros = new ApiErros(mensagemErro);
        return new ResponseEntity(apiErros, codigoStatus);
    }
}
