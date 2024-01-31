package com.mang.medisinais.domain;

import com.mang.medisinais.domain.enums.PlanoSaudeValido;
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

  @Enumerated(EnumType.STRING)
  private PlanoSaudeValido nome;

  @ManyToMany(mappedBy = "planosAceitos", cascade = CascadeType.ALL)
  private List<Profissional> profissionais;

}
