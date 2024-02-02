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

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

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

  @NotEmpty(message = "Por favor, preencha o campo de nome")
  @Column(nullable = false)
  private String nome;

  @Column(unique = true)
  private String slug;

  @NotEmpty(message = "Por favor, selecione uma especialidade")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EspecialidadeProfissional especialidade;

  @Enumerated(EnumType.STRING)
  @ManyToMany()
  @Column(name = "planos_aceitos")
  private List<PlanoSaude> planosAceitos;

  @NotEmpty(message = "Por favor, preencha o campo de email")
  @Email(message = "Email inválido")
  @Column(unique = true, nullable = false)
  private String email;

  @NotEmpty(message = "Por favor, preencha o campo de senha")
  @Column(nullable = false)
  private String senha;

  @OneToMany(mappedBy = "profissional")
  private List<Endereco> enderecos;

  @NotEmpty(message = "Por favor, preencha o campo de CPF")
  @CPF(message = "CPF inválido")
  @Column(unique = true, length = 11, nullable = false)
  @Size(min = 11, max = 11)
  private String cpf;

  @NotEmpty(message = "Por favor, preencha o campo de telefone")
  @Column(unique = true, length = 11, nullable = false)
  @Size(min = 11, max = 11)
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
