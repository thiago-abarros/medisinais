package com.mang.medisinais.domain.dto;

import com.mang.medisinais.domain.Endereco;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;

import java.util.List;

public record ProfissionalDTO(String nome, EspecialidadeProfissional especialidade,
    List<PlanoSaudeValido> planosAceitos, String email, String senha, List<Endereco> enderecos,
    String cpf, String telefone, byte[] foto) {}
