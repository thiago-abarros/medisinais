package com.mang.medisinais.dto;

import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record AtualizarProfissionalDTO(@NotEmpty(message = "Nome inválido.")
                                       String nome,
                                       List<PlanoSaudeValido> planosAceitos,
                                       CadastroEnderecoDTO endereco,
                                       @NotEmpty(message = "O telefone não pode ser vazio.")
                                       @Pattern(regexp = "(^$|\\d{11})", message = "Telefone inválido.")
                                       String telefone,
                                       byte[] foto) {

}
