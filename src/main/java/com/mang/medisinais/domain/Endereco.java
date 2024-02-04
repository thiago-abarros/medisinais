package com.mang.medisinais.domain;

import com.mang.medisinais.domain.enums.UnidadeFederativa;
import com.mang.medisinais.dto.CadastroEnderecoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_endereco")
  private UUID id;

  @Column(nullable = false)
  private String rua;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  @Size(min = 3, max = 40)
  private String cidade;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 2)
  private UnidadeFederativa uf;

  @Column(nullable = false, length = 8)
  private int cep;

  @ManyToOne
  private Profissional profissional;

  public Endereco(CadastroEnderecoDTO enderecoDTO, Profissional profissional) {
    this.rua = enderecoDTO.rua();
    this.bairro = enderecoDTO.bairro();
    this.cidade = enderecoDTO.cidade();
    this.uf = enderecoDTO.uf();
    this.cep = enderecoDTO.cep();
    this.profissional = profissional;
  }
}
