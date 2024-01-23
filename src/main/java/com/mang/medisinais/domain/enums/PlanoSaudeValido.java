package com.mang.medisinais.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum PlanoSaudeValido {

  BRADESCO("Bradesco Saúde", "bradesco", 3L),
  UNIMED("Unimed", "unimed", 1L),
  AMIL("Amil", "amil", 2L);

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
