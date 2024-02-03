package com.mang.medisinais.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(DataIntegrityViolationException.class)
  public void tratarEntradaDuplicada(DataIntegrityViolationException exception) {
//    OperacaoDTO exceptionDTO = new OperacaoDTO("Usuário já cadastrado",
//        HttpStatus.BAD_REQUEST.value());

    exception.printStackTrace();

//    return exceptionDTO.mensagem();
  }

  @ExceptionHandler(MediSinaisExcecao.class)
  public ResponseEntity<String> tratarExcecao(MediSinaisExcecao excecao) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(excecao.getMessage());
  }

}
