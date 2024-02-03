package com.mang.medisinais.dto;

import java.util.Map;

public record ResultadoCadastroDTO(boolean status, Map<String, String> mensagens) {
}
