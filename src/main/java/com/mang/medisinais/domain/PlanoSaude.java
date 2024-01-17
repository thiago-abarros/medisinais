package com.mang.medisinais.domain;

import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
