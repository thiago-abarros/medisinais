package com.mang.medisinais.services;

import com.mang.medisinais.dto.CadastroDTO;
import com.mang.medisinais.repositories.ProfissionalRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidadorService {

    private final ProfissionalRepository userRepo;

    public Map<String, String> validarProfissional(CadastroDTO profissional) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<CadastroDTO>> violacoes = validator.validate(profissional);

        Map<String, String> mensagens = new HashMap<>();
        for (ConstraintViolation<CadastroDTO> violacao : violacoes) {
            String origem = violacao.getPropertyPath().toString();
            String message = violacao.getMessage();
            mensagens.put(origem, message);
        }

        if(userRepo.findByEmail(profissional.email()) != null) {
            mensagens.put("email", "Email já cadastrado.");
        }
        if(userRepo.findByCpf(profissional.cpf()) != null) {
            mensagens.put("cpf", "CPF já cadastrado.");
        }

        return mensagens;
    }

}
