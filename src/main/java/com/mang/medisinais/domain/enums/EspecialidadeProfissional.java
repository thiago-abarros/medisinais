package com.mang.medisinais.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EspecialidadeProfissional {

  MEDICO("medico"),
  PSICOLOGO("psicologo"),
  NUTRICIONISTA("nutricionista");

  private final String nome;

  public static EspecialidadeProfissional valueOfNome(String nome) {
    for(EspecialidadeProfissional e : values()) {
      if(e.getNome().equals(nome)){
        return e;
      }
    }
    return null;
  }

}
