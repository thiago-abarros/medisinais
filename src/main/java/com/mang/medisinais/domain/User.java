package com.mang.medisinais.domain;

import com.mang.medisinais.domain.dto.UserDTO;
import com.mang.medisinais.domain.enums.HealthInsurancePlan;
import com.mang.medisinais.domain.enums.ProfessionalType;
import com.mang.medisinais.utility.Slug;
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

  @Column(name = "user_name", nullable = false)
  private String name;

  @Column(name = "user_slug", unique = true)
  private String slug;

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
  @OneToMany(mappedBy = "users")
  private Address address;

  @Column(unique = true)
  private String cpf;

  @Column(unique = true)
  private String cellphone;

  @Column(name = "user_photo")
  private byte[] photo;

  public User(UserDTO userDTO) {
    this.name = userDTO.getName();
    this.email = userDTO.getEmail();
    this.cpf = userDTO.getCpf();
    this.address = userDTO.getAddress();
    this.cellphone = userDTO.getCellphone();
    this.healthInsurancePlan = userDTO.getHealthInsurancePlan();
    this.professionalType = userDTO.getProfessionalType();
    this.password = userDTO.getPassword();
    this.photo = userDTO.getPhoto();
    this.slug = Slug.makeSlug(userDTO.name());
  }
}
