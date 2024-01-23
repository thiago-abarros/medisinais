package com.mang.medisinais.domain;

import com.mang.medisinais.domain.enums.UnidadeFederativa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_endereco")
  private Long id;

  @Column(nullable = false)
  private String rua;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  @Size(min = 3, max = 40)
  private String cidade;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 2)
  @Size(min = 2, max = 2)
  private UnidadeFederativa uf;

  @Column(nullable = false, length = 8)
  @Size(min = 8, max = 8)
  private String cep;

  @ManyToOne
  private Profissional profissional;

}
