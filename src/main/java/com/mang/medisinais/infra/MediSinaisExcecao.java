package com.mang.medisinais.infra;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MediSinaisExcecao extends Exception {

  public MediSinaisExcecao(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }

  public MediSinaisExcecao(String mensagem) {
    super(mensagem);
  }
}
