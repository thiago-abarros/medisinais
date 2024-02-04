package com.mang.medisinais.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanoSaudeValido {

  UNIMED("Unimed", "Unimed", 1L),
  BRADESCO("Bradesco Saúde", "Bradesco", 2L),
  AMIL("Amil", "Amil", 3L),
  SULAMERICA("Sul América", "SulAmerica", 4L),
  HAPVIDA("HapVida", "HapVida", 5L);

  private final String nome;
  private final String slug;
  private final Long id;

  public static PlanoSaudeValido valueOfNome(String nome) {
    for (PlanoSaudeValido p : values()) {
      if (p.getSlug().equals(nome)) {
        return p;
      }
    }
    return null;
  }

}
