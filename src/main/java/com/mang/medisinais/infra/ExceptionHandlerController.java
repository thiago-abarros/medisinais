package com.mang.medisinais.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<String> tratarEntradaDuplicada(DataIntegrityViolationException exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
  }

  @ExceptionHandler(MediSinaisExcecao.class)
  public ResponseEntity<String> tratarExcecao(MediSinaisExcecao excecao) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(excecao.getMessage());
  }

}
