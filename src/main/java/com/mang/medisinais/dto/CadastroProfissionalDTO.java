package com.mang.medisinais.dto;

import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.multipart.MultipartFile;

public record CadastroProfissionalDTO(@NotEmpty(message = "Nome inválido.")
                                      String nome,
                                      EspecialidadeProfissional especialidade,
                                      List<PlanoSaudeValido> planosAceitos,
                                      @NotEmpty(message = "O email não pode ser vazio.")
                                      @Email(message = "Email inválido.")
                                      String email,
                                      @Size(min = 8, max = 32, message
                                          = "A senha deve conter no mínimo 8 e no máximo 32 caracteres.")
                                      String senha,
                                      CadastroEnderecoDTO endereco,
                                      @NotEmpty(message = "CPF inválido.")
                                      @CPF(message = "CPF inválido.")
                                      String cpf,
                                      @NotEmpty(message = "O telefone não pode ser vazio.")
                                      @Pattern(regexp = "(^$|\\d{11})", message = "Telefone inválido.")
                                      String telefone,
                                      MultipartFile foto) {

}
