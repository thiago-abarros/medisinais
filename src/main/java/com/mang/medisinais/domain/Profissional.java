package com.mang.medisinais.domain;

import com.mang.medisinais.domain.dto.ProfissionalDTO;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.utility.Slug;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

  private String nome;

  @Column(unique = true)
  private String slug;

  @Enumerated(EnumType.STRING)
  private EspecialidadeProfissional especialidade;

  @Enumerated(EnumType.STRING)
  @ManyToMany(mappedBy = "profissionais")
  @Column(name = "planos_aceitos")
  private List<PlanoSaude> planosAceitos;

  @Column(unique = true)
  private String email;

  private String senha;

  @OneToMany(mappedBy = "profissional")
  private List<Endereco> enderecos;

  @Column(unique = true)
  private String cpf;

  @Column(unique = true)
  private String telefone;

  @Column(name = "foto_usuario")
  private byte[] foto;

  public Profissional(ProfissionalDTO profissionalDTO) {
    this.nome = profissionalDTO.nome();
    this.email = profissionalDTO.email();
    this.cpf = profissionalDTO.cpf();
    this.enderecos = profissionalDTO.enderecos();
    this.telefone = profissionalDTO.telefone();
    this.planosAceitos = profissionalDTO.planosAceitos().stream().map(PlanoSaude::new).toList();
    this.especialidade = profissionalDTO.especialidade();
    this.senha = profissionalDTO.senha();
    this.foto = profissionalDTO.foto();
    this.slug = Slug.makeSlug(profissionalDTO.nome());
  }

}
