package com.mang.medisinais.domain;

import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

  @ManyToMany
  private List<Profissional> profissionais;

  public PlanoSaude(PlanoSaudeValido plano) {
    this.nome = String.valueOf(plano);
  }

}
