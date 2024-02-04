package com.mang.medisinais.dto;

import java.util.UUID;

public record OperacaoDTO(boolean status, String mensagem, UUID idProfissional) {

}
