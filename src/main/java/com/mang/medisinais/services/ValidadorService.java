package com.mang.medisinais.services;

import com.mang.medisinais.dto.CadastroProfissionalDTO;
import com.mang.medisinais.repositories.ProfissionalRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidadorService {

  private final ProfissionalRepository userRepo;

  public Map<String, String> validarProfissional(CadastroProfissionalDTO profissional) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Set<ConstraintViolation<CadastroProfissionalDTO>> violacoes = validator.validate(profissional);

    Map<String, String> mensagens = new HashMap<>();
    for (ConstraintViolation<CadastroProfissionalDTO> violacao : violacoes) {
      String origem = violacao.getPropertyPath().toString();
      String message = violacao.getMessage();
      mensagens.put(origem, message);
    }

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

}
