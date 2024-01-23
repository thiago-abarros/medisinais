package com.mang.medisinais.domain;

import com.github.slugify.Slugify;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.dto.ProfissionalDTO;
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
import java.util.List;
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

  public Profissional(ProfissionalDTO profissionalDTO, String senha, List<PlanoSaude> planos) {
    this.nome = profissionalDTO.nome();
    this.email = profissionalDTO.email();
    this.cpf = profissionalDTO.cpf();
    this.enderecos = profissionalDTO.enderecos();
    this.telefone = profissionalDTO.telefone();
    this.planosAceitos = planos;
    this.especialidade = profissionalDTO.especialidade();
    this.senha = senha;
    this.foto = profissionalDTO.foto();
    this.slug = Slugify.builder().build().slugify(profissionalDTO.nome());
  }

}
