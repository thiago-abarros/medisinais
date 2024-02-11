package com.mang.medisinais.dto;

import com.mang.medisinais.domain.Endereco;
import com.mang.medisinais.domain.PlanoSaude;
import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import java.util.List;

public record ResultadoDTO(String nome, EspecialidadeProfissional especialidade,
                           List<PlanoSaude> planosAceitos, String email, Endereco endereco,
                           String telefone, byte[] foto, String slug) {

  public static ResultadoDTO fromProfissional(Profissional profissional) {
    return new ResultadoDTO(
        profissional.getNome(), profissional.getEspecialidade(),
        profissional.getPlanosAceitos(), profissional.getEmail(), profissional.getEndereco(),
        profissional.getTelefone(), profissional.getFoto(), profissional.getSlug()
    );
  }

}
