package com.mang.medisinais.dto;

import com.mang.medisinais.domain.enums.UnidadeFederativa;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastroEnderecoDTO(@NotEmpty(message = "A rua não pode ser vazia.")
                                  String rua,
                                  @NotEmpty(message = "O bairro não pode ser vazio.")
                                  String bairro,
                                  @Size(min = 3, max = 40, message = "Cidade inválida.")
                                  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Cidade inválida.")
                                  String cidade,
                                  UnidadeFederativa uf,
                                  @NotEmpty(message = "O cep não pode ser vazio.")
                                  @Pattern(regexp = "(^$|\\d{8})", message = "Cep inválido.")
                                  String cep) {

}
