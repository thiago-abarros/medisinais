package com.mang.medisinais.services;

import com.mang.medisinais.dto.CadastroEnderecoDTO;
import com.mang.medisinais.dto.CadastroProfissionalDTO;
import com.mang.medisinais.repositories.ProfissionalRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidadorService {

  private final ProfissionalRepository userRepo;
  private final Validator validator;

  public Map<String, String> validarProfissional(CadastroProfissionalDTO profissional) {
    Map<String, String> mensagens = new HashMap<>();

    Set<ConstraintViolation<CadastroProfissionalDTO>> violacoesProfissional = validator.validate(profissional);
    Set<ConstraintViolation<CadastroEnderecoDTO>> violacoesEndereco = validator.validate(profissional.endereco());

    mensagens.putAll(pegarViolacao(violacoesProfissional));
    mensagens.putAll(pegarViolacao(violacoesEndereco));

    if (userRepo.findByEmail(profissional.email()) != null) {
      mensagens.put("email", "Email já cadastrado.");
    }
    if (userRepo.findByCpf(profissional.cpf()) != null) {
      mensagens.put("cpf", "CPF já cadastrado.");
    }
    if (userRepo.findByTelefone(profissional.telefone()) != null) {
      mensagens.put("telefone", "Telefone já cadastrado");
    }

    return mensagens;
  }

  public Map<String, String> pegarViolacao(Set<? extends ConstraintViolation<?>> violacoes) {
    Map<String, String> mensagens = new HashMap<>();

    violacoes.forEach(violacao -> {
      String origem = violacao.getPropertyPath().toString();
      String message = violacao.getMessage();
      mensagens.put(origem, message);
    });

    return mensagens;
  }

}
