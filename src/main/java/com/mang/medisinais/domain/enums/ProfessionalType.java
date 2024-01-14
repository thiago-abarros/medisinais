package com.mang.medisinais.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfessionalType {
  MEDIC("Médico"),
  PSYCHOLOGIST("Psicólogo");

  private final String name;
}
