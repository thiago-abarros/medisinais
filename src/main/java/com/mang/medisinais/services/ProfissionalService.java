package com.mang.medisinais.services;

import com.mang.medisinais.domain.Endereco;
import com.mang.medisinais.domain.PlanoSaude;
import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import com.mang.medisinais.dto.AtualizarProfissionalDTO;
import com.mang.medisinais.dto.CadastroProfissionalDTO;
import com.mang.medisinais.dto.FiltroDTO;
import com.mang.medisinais.dto.LoginDTO;
import com.mang.medisinais.dto.OperacaoDTO;
import com.mang.medisinais.dto.ResultadoOpProfissionalDTO;
import com.mang.medisinais.dto.ResultadoDTO;
import com.mang.medisinais.infra.MediSinaisExcecao;
import com.mang.medisinais.repositories.EnderecoRepository;
import com.mang.medisinais.repositories.PlanoSaudeRepository;
import com.mang.medisinais.repositories.ProfissionalRepository;
import com.mang.medisinais.specifications.ProfissionalSpecification;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

  private static final String EMAIL_PROFISSIONAL_NAO_ENCONTRADO = "Email não encontrado.";
  private static final String SENHA_INCORRETA = "Senha incorreta.";
  private static final String PROFISSIONAL_NAO_ENCONTRADO = "Profissional não encontrado.";
  private static final int PROFISSIONAIS_POR_PAGINA = 5;

  private final BCryptPasswordEncoder bcrypt;
  private final ProfissionalRepository userRepo;
  private final EnderecoRepository enderecoRepo;
  private final PlanoSaudeRepository planoRepo;
  private final ValidadorService validador;

  public ResultadoOpProfissionalDTO criarProfissional(CadastroProfissionalDTO dados) {
    Map<String, String> mensagens = validador.validarProfissional(dados);

    if (!mensagens.isEmpty()) {
      return new ResultadoOpProfissionalDTO(false, mensagens);
    }

    String senhaHash = bcrypt.encode(dados.senha());
    List<PlanoSaude> planos = Optional.ofNullable(dados.planosAceitos())
            .map(list -> list.stream().map(PlanoSaude::new).toList())
            .orElse(Collections.emptyList());

    Profissional novoProfissional = new Profissional(dados, senhaHash, planos);
    Endereco novoEndereco = new Endereco(dados.endereco(), novoProfissional);

    this.userRepo.save(novoProfissional);
    this.enderecoRepo.save(novoEndereco);

    return new ResultadoOpProfissionalDTO(true, mensagens);
  }

  public ResultadoOpProfissionalDTO alterarProfissional(UUID idProfissional, AtualizarProfissionalDTO dados)
      throws MediSinaisExcecao {
    Map<String, String> mensagens = validador.validarEdicao(idProfissional, dados);

    if (!mensagens.isEmpty()) {
      return new ResultadoOpProfissionalDTO(false, mensagens);
    }

    Profissional profissional = encontrarProfissionalPorId(idProfissional);
    Endereco endereco = enderecoRepo.getReferenceById(profissional.getEndereco().getId());

    List<PlanoSaude> planos = Optional.ofNullable(dados.planosAceitos())
            .map(list -> list.stream().map(PlanoSaude::new).toList())
            .orElse(Collections.emptyList());

    List<PlanoSaude> planosArrayList = new ArrayList<>(planos);

    BeanUtils.copyProperties(dados,profissional);
    BeanUtils.copyProperties(dados.endereco(), endereco);
    profissional.setPlanosAceitos(planosArrayList);

    this.userRepo.save(profissional);
    this.enderecoRepo.save(endereco);

    return new ResultadoOpProfissionalDTO(true, mensagens);
  }

  public Page<Profissional> pesquisaProfissionais(FiltroDTO filtro) {
    var especialidade = EspecialidadeProfissional.valueOfNome(filtro.especialidade());
    var plano = PlanoSaudeValido.valueOfNome(filtro.planoSaude());
    int pagina = filtro.pagina() != null ? Integer.parseInt(filtro.pagina()) - 1 : 0;
    var paginacao = PageRequest.of(pagina, PROFISSIONAIS_POR_PAGINA);

      return userRepo.findAll(Specification
              .where(ProfissionalSpecification.temCidade(filtro.cidade())
              .and(ProfissionalSpecification.temEspecialidade(especialidade)))
              .and(ProfissionalSpecification.temPlanoSaude(plano)), paginacao);
  }

  public ResultadoDTO encontrarProfissionalPorSlug(String slug) throws MediSinaisExcecao {
    Profissional resultado = userRepo.findBySlug(slug);

    if (resultado == null) {
      throw new MediSinaisExcecao(PROFISSIONAL_NAO_ENCONTRADO);
    }

    return ResultadoDTO.fromProfissional(resultado);
  }

  public Profissional encontrarProfissionalPorId(UUID id) throws MediSinaisExcecao {
    Optional<Profissional> resultado = userRepo.findById(id);

    if (resultado.isEmpty()) {
      throw new MediSinaisExcecao(PROFISSIONAL_NAO_ENCONTRADO);
    }

    return resultado.get();
  }

  public OperacaoDTO autenticarProfissional(LoginDTO dadosLogin) {
    Profissional profissional = this.userRepo.findByEmail(dadosLogin.email());

    if (profissional == null) {
      return new OperacaoDTO(false, EMAIL_PROFISSIONAL_NAO_ENCONTRADO, null);
    }
    if (!bcrypt.matches(dadosLogin.senha(), profissional.getSenha())) {
      return new OperacaoDTO(false, SENHA_INCORRETA, null);
    }

    return new OperacaoDTO(true, "OK", profissional.getId());
  }

}
