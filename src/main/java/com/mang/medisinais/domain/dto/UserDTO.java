package com.mang.medisinais.domain.dto;

import com.mang.medisinais.domain.Address;
import com.mang.medisinais.domain.enums.HealthInsurancePlan;
import com.mang.medisinais.domain.enums.ProfessionalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public record UserDTO(
    String name,
    ProfessionalType professionalType,
    List<HealthInsurancePlan> healthInsurancePlan,
    String email,
    String password,
    Address address,
    String cpf,
    String cellphone,
    byte[] photo) {}
