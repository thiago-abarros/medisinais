package com.mang.medisinais.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanoSaudeValido {

  BRADESCO("Bradesco Saúde", "bradesco",3L),
  UNIMED("Unimed", "unimed", 1L),
  AMIL("Amil", "amil", 2L);

  private final String nome;
  private final String slug;
  private final Long id;

  public static Long valueOfNome(String nome) {
    for(PlanoSaudeValido p : values()) {
      if(p.getSlug().equals(nome)) {
        return p.getId();
      }
    }
    return 0L;
  }

}
