package com.mang.medisinais.domain;

import com.mang.medisinais.domain.enums.HealthInsurancePlan;
import com.mang.medisinais.domain.enums.ProfessionalType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "user_name")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "professional_type")
  private ProfessionalType professionalType;

  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "user_health_insurance", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "health_insurance_plan")
  private List<HealthInsurancePlan> healthInsurancePlan;

  @Column(unique = true)
  private String email;

  @Column(name = "user_password")
  private String password;

  @Column(name = "user_address")
  @ManyToOne
  private Address address;

  @Column(unique = true)
  private String cpf;

  @Column(unique = true)
  private String cellphone;

  @Column(name = "user_photo")
  private byte[] photo;
}
