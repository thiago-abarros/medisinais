package com.mang.medisinais.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanoSaudeValido {

  BRADESCO("Bradesco Saúde", "bradesco"),
  UNIMED("Unimed", "unimed"),
  AMIL("Amil", "amil");

  private final String nome;
  private final String slug;

  public static PlanoSaudeValido valueOfNome(String nome) {
    for(PlanoSaudeValido p : values()) {
      if(p.getSlug().equals(nome)) {
        return p;
      }
    }
    return null;
  }

}
