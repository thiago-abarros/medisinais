package com.mang.medisinais.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HealthInsurancePlan {
  BRADESCO("Bradesco Saúde"),
  UNIMED("Unimed"),
  AMIL("Amil");

  private final String name;
}
