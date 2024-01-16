package com.mang.medisinais.domain;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "address_id")
  private Long id;

  @Column(name = "building_number")
  private Integer buildingNumber;

  private String cep;

  private String street;

  private String neighbourhood;

  private String city;

  private String state;

  @ManyToOne
  private User user;
}
