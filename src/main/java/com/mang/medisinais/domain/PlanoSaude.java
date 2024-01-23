package com.mang.medisinais.domain;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "planoSaude")
@Data
public class PlanoSaude {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_plano_saude")
  private Long id;

  private String nome;

  @ManyToMany(mappedBy = "planosAceitos", cascade = CascadeType.ALL)
  private List<Profissional> profissionais;

}
