package com.mang.medisinais.domain.dto;

import com.mang.medisinais.domain.Endereco;
import com.mang.medisinais.domain.PlanoSaude;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;

import java.util.List;

public record UserDTO(
    String nome,
    EspecialidadeProfissional especialidade,
    List<PlanoSaude> planosAceitos,
    String email,
    String senha,
    List<Endereco> enderecos,
    String cpf,
    String telefone,
    byte[] foto) {}
