package com.mang.medisinais.domain;

import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

  @Enumerated(EnumType.STRING)
  private PlanoSaudeValido nome;

  @ManyToMany(mappedBy = "planosAceitos", cascade = CascadeType.ALL)
  private List<Profissional> profissionais;

  public PlanoSaude(PlanoSaudeValido planoSaudeValido) {
    this.id = planoSaudeValido.getId();
    this.nome = planoSaudeValido;
  }

}
