package com.mang.medisinais.domain.dto;

import com.mang.medisinais.domain.Address;
import com.mang.medisinais.domain.enums.HealthInsurancePlan;
import com.mang.medisinais.domain.enums.ProfessionalType;

import java.util.List;

public record UserDTO(
    String name,
    ProfessionalType professionalType,
    List<HealthInsurancePlan> healthInsurancePlan,
    String email,
    String password,
    List<Address> address,
    String cpf,
    String cellphone,
    byte[] photo) {}
