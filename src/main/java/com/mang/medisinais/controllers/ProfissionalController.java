package com.mang.medisinais.controllers;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.dto.*;
import com.mang.medisinais.infra.MediSinaisExcecao;
import com.mang.medisinais.services.ProfissionalService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProfissionalController {

  private final ProfissionalService profissionalService;

  @GetMapping("/")
  public String exibirHome(HttpServletRequest request, Model model) {
    HttpSession sessao = request.getSession(false);

    model.addAttribute("logado", sessao != null);

    return "home";
  }

  @GetMapping("/cadastro")
  public String exibirPaginaCadastro() {
    return "cadastro";
  }

  @PostMapping("/cadastro")
  public ResponseEntity<Map<String, String>> cadastrarProfissional(CadastroDTO dadosProfissional) {
    ResultadoCadastroDTO resultadoCadastroDTO = profissionalService.criarProfissional(dadosProfissional);

    if(!resultadoCadastroDTO.status()) {
      System.out.println(resultadoCadastroDTO.mensagens());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultadoCadastroDTO.mensagens());
    }

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/login")
  public ResponseEntity<String> logarProfissional(LoginDTO login, HttpServletRequest request) throws MediSinaisExcecao {
    OperacaoDTO resultadoLogin = profissionalService.autenticarProfissional(login);

    if(!resultadoLogin.status()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultadoLogin.mensagem());
    }

    HttpSession sessao = request.getSession(true);
    sessao.setAttribute("idProfissional", resultadoLogin.idProfissional());

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/home")
  public String exibirHomeProfissional(Model model, HttpSession sessao) {
    Profissional profissional = (Profissional) sessao.getAttribute("dadosProfissional");

    model.addAttribute("profissional", profissional);

    return "paginaInicial";
  }

  @GetMapping("/pesquisa")
  public String exibirPesquisaProfissionais(FiltroDTO filtroDTO, Model model, HttpServletRequest request) {
    List<Profissional> profissionais = profissionalService.pesquisaProfissionais(filtroDTO);
    HttpSession sessao = request.getSession(false);

    model.addAttribute("logado", sessao != null);
    model.addAttribute("profissionais", profissionais);

    return "listaProfissionais";
  }

  @GetMapping("/profissional/{slug}")
  public String exibirPaginaProfissional(@PathVariable String slug, Model model,
                                         HttpServletRequest request) throws MediSinaisExcecao {
    ResultadoDTO profissional = profissionalService.encontrarPorSlug(slug);
    HttpSession sessao = request.getSession(false);

    model.addAttribute("profissional", profissional);
    model.addAttribute("logado", sessao != null);

    return "paginaProfissional";
  }

  @GetMapping("/logout")
  public void realizarLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession sessao = request.getSession(false);

    if(sessao != null) {
      sessao.invalidate();
      response.sendRedirect("/");
    }
  }

}
