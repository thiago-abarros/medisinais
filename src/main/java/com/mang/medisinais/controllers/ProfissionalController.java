package com.mang.medisinais.controllers;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.dto.FiltroDTO;
import com.mang.medisinais.domain.dto.LoginDTO;
import com.mang.medisinais.domain.dto.ProfissionalDTO;
import com.mang.medisinais.domain.dto.ResultadoDTO;
import com.mang.medisinais.services.ProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProfissionalController {

  private final ProfissionalService profissionalService;

  @PostMapping("/cadastro")
  public ResponseEntity<ProfissionalDTO> registrarProfissional(@RequestBody ProfissionalDTO profissionalDTO) {
    return null;
  }

  @PostMapping("/login")
  public RequestEntity<ProfissionalDTO> loginProfissional(@RequestBody LoginDTO login) {
    return null;
  }

  @GetMapping("/pesquisa")
  public String pesquisaProfissionais (FiltroDTO filtroDTO, ModelMap model) {

    List<Profissional> profissionais = profissionalService.pesquisaProfissionais(filtroDTO);
    model.addAttribute("profissionais", profissionais);

    return "listaProfissionais";
  }

  @GetMapping("/profissional/{slug}")
  public String paginaProfissional(@PathVariable String slug, ModelMap model) {
    ResultadoDTO profissional = profissionalService.encontrarPorSlug(slug);

    if(profissional == null) {
      return "erro";
    }

    model.addAttribute("profissional", profissional);

    return "paginaProfissional";
  }

}
