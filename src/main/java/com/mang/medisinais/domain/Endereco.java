package com.mang.medisinais.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_endereco")
  private Long id;

  private String rua;

  private String bairro;

  private String cidade;

  private String uf;

  private String cep;

  @ManyToOne
  private Profissional profissional;

  public Endereco(String rua, String bairro, String cidade, String uf, String cep) {
    this.rua = rua;
    this.bairro = bairro;
    this.cidade = cidade;
    this.uf = uf;
    this.cep = cep;
  }
}
