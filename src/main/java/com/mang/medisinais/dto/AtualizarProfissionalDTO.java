package com.mang.medisinais.dto;

import com.mang.medisinais.domain.PlanoSaude;
import java.util.List;

public record AtualizarProfissionalDTO(String nome, List<PlanoSaude> planosAceitos,
                                       String email, String senha, CadastroEnderecoDTO endereco,
                                       String telefone, byte[] foto) {

}
