package com.mang.medisinais.dto;

import com.mang.medisinais.domain.enums.UnidadeFederativa;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastroEnderecoDTO(@NotEmpty(message = "A rua não pode ser vazia.")
                                  String rua,
                                  @NotEmpty(message = "O bairro não pode ser vazio.")
                                  String bairro,
                                  @NotEmpty(message = "A cidade não pode ser vazia.")
                                  @Size(min = 3, max = 40)
                                  String cidade,
                                  @NotEmpty(message = "Selecione uma unidade federativa")
                                  UnidadeFederativa uf,
                                  @NotEmpty(message = "O cep não pode ser vazio.")
                                  @Pattern(regexp = "(^$|\\d{8})", message = "cep inválido.")
                                  int cep
) {

}
