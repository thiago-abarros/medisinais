package com.mang.medisinais.domain;

import com.github.slugify.Slugify;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.dto.CadastroDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "profissional")
public class Profissional {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_profissional")
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true)
  private String slug;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EspecialidadeProfissional especialidade;

  @Enumerated(EnumType.STRING)
  @ManyToMany()
  @Column(name = "planos_aceitos")
  private List<PlanoSaude> planosAceitos;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @OneToMany(mappedBy = "profissional")
  private List<Endereco> enderecos;

  @Column(unique = true, length = 11, nullable = false)
  @Size(min = 11, max = 11)
  private String cpf;

  @Column(unique = true, length = 11, nullable = false)
  @Size(min = 11, max = 11)
  private String telefone;

  @Column(name = "foto_usuario")
  private byte[] foto;

  public Profissional(CadastroDTO profissionalDTO, String senha, List<PlanoSaude> planos) {
    this.nome = profissionalDTO.nome();
    this.email = profissionalDTO.email();
    this.cpf = profissionalDTO.cpf();
    profissionalDTO.endereco().setProfissional(this);
    this.enderecos = Arrays.asList(profissionalDTO.endereco());
    this.telefone = profissionalDTO.telefone();
    this.planosAceitos = planos;
    this.especialidade = profissionalDTO.especialidade();
    this.senha = senha;
    this.foto = profissionalDTO.foto();
    this.slug = Slugify.builder().build().slugify(profissionalDTO.nome());
  }
}
