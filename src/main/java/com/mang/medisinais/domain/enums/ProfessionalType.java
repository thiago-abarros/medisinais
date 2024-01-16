package com.mang.medisinais.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfessionalType {
  DOCTOR("Médico"),
  PSYCHOLOGIST("Psicólogo"),
  NUTRICIAN("Nutricionista");

  private final String name;
}
