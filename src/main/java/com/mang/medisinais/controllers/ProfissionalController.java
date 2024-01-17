package com.mang.medisinais.controllers;

import com.mang.medisinais.domain.Endereco;
import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.dto.LoginDTO;
import com.mang.medisinais.domain.dto.ProfissionalDTO;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import com.mang.medisinais.repositories.UserRepository;
import com.mang.medisinais.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @Autowired
  UserRepository userRepository;

  @PostMapping("/cadastro")
  public ResponseEntity<ProfissionalDTO> userRegister(@RequestBody ProfissionalDTO profissionalDTO) {
    return null;
  }

  @PostMapping("/login")
  public RequestEntity<ProfissionalDTO> userLogin(@RequestBody LoginDTO login) {
    return null;
  }

  @GetMapping("/testeCadastro")
  public String testeCadastro() {
    ProfissionalDTO profissionalDTO = new ProfissionalDTO("Pedro", EspecialidadeProfissional.DOCTOR,
            Arrays.asList(PlanoSaudeValido.BRADESCO, PlanoSaudeValido.UNIMED), "pedro@email.com", "1234",
            Arrays.asList(new Endereco("Rua LD", "Centro", "Recife", "PE", "32442")),
            "032049224243", "8438498324", "".getBytes());

    Profissional prof = new Profissional(profissionalDTO);

    userRepository.save(prof);

    userRepository.findAll();

    return "ok";
  }

}
