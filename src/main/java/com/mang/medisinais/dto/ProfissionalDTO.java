package com.mang.medisinais.dto;

import com.mang.medisinais.domain.Endereco;
import com.mang.medisinais.domain.PlanoSaude;
import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import java.util.List;

public record ProfissionalDTO(String nome, EspecialidadeProfissional especialidade,
                              List<PlanoSaudeValido> planosAceitos, String email, String senha,
                              Endereco endereco,
                              String cpf, String telefone, byte[] foto) {

    public static ProfissionalDTO fromProfissional(Profissional profissional) {
        return new ProfissionalDTO(
                profissional.getNome(), profissional.getEspecialidade(),
                profissional.getPlanosAceitos().stream().map(PlanoSaude::getNome).toList(),
                profissional.getEmail(), profissional.getSenha(),
                profissional.getEndereco(), profissional.getCpf(), profissional.getTelefone(), profissional.getFoto()
        );
    }

}
