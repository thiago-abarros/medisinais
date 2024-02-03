package com.mang.medisinais.dto;

import com.mang.medisinais.domain.Endereco;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record CadastroDTO(@NotEmpty(message = "Nome inválido.")
                          String nome,
                          EspecialidadeProfissional especialidade,
                          List<PlanoSaudeValido> planosAceitos,
                          @NotEmpty(message = "O email não pode ser vazio.")
                          @Email(message = "Email inválido.")
                          String email,
                          @Size(min = 8, max = 32, message
                                  = "A senha deve conter no mínimo 8 e no máximo 32 caracteres.")
                          String senha,
                          Endereco endereco,
                          @NotEmpty(message = "CPF inválido.")
                          @CPF(message = "CPF inválido.")
                          String cpf,
                          @NotEmpty(message = "O telefone não pode ser vazio.")
                          @Pattern(regexp="(^$|[0-9]{11})", message = "Telefone inválido.")
                          String telefone,
                          byte[] foto) {

}
