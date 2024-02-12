package com.mang.medisinais.controllers;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.dto.*;
import com.mang.medisinais.infra.MediSinaisExcecao;
import com.mang.medisinais.services.ProfissionalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
  public ResponseEntity<Map<String, String>> cadastrarProfissional(
      CadastroProfissionalDTO dadosProfissional) {
    ResultadoOpProfissionalDTO resultadoOpProfissionalDTO = profissionalService.criarProfissional(
        dadosProfissional);

    if (!resultadoOpProfissionalDTO.status()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultadoOpProfissionalDTO.mensagens());
    }

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/login")
  public ResponseEntity<String> logarProfissional(LoginDTO login, HttpServletRequest request) {
    OperacaoDTO resultadoLogin = profissionalService.autenticarProfissional(login);

    if (!resultadoLogin.status()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultadoLogin.mensagem());
    }

    HttpSession sessao = request.getSession(true);
    sessao.setAttribute("idProfissional", resultadoLogin.idProfissional());

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/editar-dados")
  public String exibirPaginaEditarDados(HttpSession sessao, Model model) throws MediSinaisExcecao {
    UUID idProfissional = (UUID) sessao.getAttribute("idProfissional");

    Profissional profissional = profissionalService.encontrarProfissionalPorId(idProfissional);

    model.addAttribute("profissional", ProfissionalDTO.fromProfissional(profissional));

    return "editarDados";
  }

  @PostMapping("/editar-dados")
  public ResponseEntity<Map<String, String>> alterarProfissional(HttpSession sessao,
      AtualizarProfissionalDTO dadosProfissional, HttpServletRequest request)
      throws MediSinaisExcecao {

    UUID idProfissional = (UUID) sessao.getAttribute("idProfissional");
    ResultadoOpProfissionalDTO resultadoAlteracao = profissionalService.alterarProfissional(idProfissional,
        dadosProfissional);

    if (!resultadoAlteracao.status()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultadoAlteracao.mensagens());
    }

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/pesquisa")
  public String exibirPesquisaProfissionais(FiltroDTO filtroDTO, Model model,
      HttpServletRequest request) {
    Page<Profissional> profissionais = profissionalService.pesquisaProfissionais(filtroDTO);

    HttpSession sessao = request.getSession(false);

    model.addAttribute("logado", sessao != null);
    model.addAttribute("profissionais", profissionais);

    return "listaProfissionais";
  }

  @GetMapping("/profissional/{slug}")
  public String exibirPaginaProfissional(@PathVariable String slug, Model model,
      HttpServletRequest request) throws MediSinaisExcecao {
    ResultadoDTO profissional = profissionalService.encontrarProfissionalPorSlug(slug);
    HttpSession sessao = request.getSession(false);
    String foto = Base64.encodeBase64String(profissional.foto());

    model.addAttribute("profissional", profissional);
    model.addAttribute("foto", foto);
    model.addAttribute("logado", sessao != null);

    return "paginaProfissional";
  }

  @GetMapping("/logout")
  public void realizarLogout(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    HttpSession sessao = request.getSession(false);

    if (sessao != null) {
      sessao.invalidate();
      response.sendRedirect("/");
    }
  }

}
