package com.mang.medisinais.controllers;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.dto.FiltroDTO;
import com.mang.medisinais.dto.LoginDTO;
import com.mang.medisinais.dto.ProfissionalDTO;
import com.mang.medisinais.dto.ResultadoDTO;
import com.mang.medisinais.infra.MediSinaisExcecao;
import com.mang.medisinais.services.ProfissionalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ProfissionalController {

  private final ProfissionalService profissionalService;

  @PostMapping("/cadastro")
  public String cadastrarProfissional
      (@RequestBody ProfissionalDTO dadosProfissional, ModelMap model) {

    ProfissionalDTO novoProfissional = profissionalService.criarProfissional(dadosProfissional);
    model.addAttribute("cadastrado", novoProfissional);

    return "paginaCadastrado";
  }

  @PostMapping("/login")
  public String loginProfissional(@RequestBody LoginDTO login, ModelMap model)
      throws MediSinaisExcecao {

    Profissional profissionalLogado = profissionalService.autenticarProfissional(login);
    model.addAttribute("logado", profissionalLogado);

    return "paginaInicial";
  }

  @GetMapping("/pesquisa")
  public String pesquisaProfissionais(FiltroDTO filtroDTO, ModelMap model) {

    List<Profissional> profissionais = profissionalService.pesquisaProfissionais(filtroDTO);
    model.addAttribute("profissionais", profissionais);

    return "listaProfissionais";
  }

  @GetMapping("/profissional/{slug}")
  public String paginaProfissional(@PathVariable String slug, ModelMap model) {
    ResultadoDTO profissional = profissionalService.encontrarPorSlug(slug);

    if (profissional == null) {
      return "erro";
    }

    model.addAttribute("profissional", profissional);

    return "paginaProfissional";
  }

}
