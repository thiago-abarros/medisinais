package com.mang.medisinais.services;

import com.mang.medisinais.domain.PlanoSaude;
import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import com.mang.medisinais.dto.*;
import com.mang.medisinais.infra.MediSinaisExcecao;
import com.mang.medisinais.repositories.EnderecoRepository;
import com.mang.medisinais.repositories.PlanoSaudeRepository;
import com.mang.medisinais.repositories.ProfissionalRepository;
import com.mang.medisinais.specifications.ProfissionalSpecification;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

  private static final String EMAIL_PROFISSIONAL_NAO_ENCONTRADO = "Email não encontrado.";
  private static final String SENHA_INCORRETA = "Senha incorreta.";
  private static final String SLUG_NAO_ENCONTRADO = "Profissional não encontrado.";

  private final BCryptPasswordEncoder bcrypt;
  private final ProfissionalRepository userRepo;
  private final EnderecoRepository enderecoRepo;
  private final PlanoSaudeRepository planoRepo;
  private final ValidadorService validador;

  public ResultadoCadastroDTO criarProfissional(CadastroDTO dados) {
    Map<String, String> mensagens = validador.validarProfissional(dados);

    if(!mensagens.isEmpty()) {
      return new ResultadoCadastroDTO(false, mensagens);
    }

    String senhaHash = bcrypt.encode(dados.senha());
    List<Long> ids = dados.planosAceitos().stream().map(PlanoSaudeValido::getId).toList();
    List<PlanoSaude> planos = planoRepo.findAllById(ids);

    Profissional novoProfissional = new Profissional(dados, senhaHash, planos);

    this.userRepo.save(novoProfissional);

    dados.endereco().setProfissional(novoProfissional);
    enderecoRepo.save(dados.endereco());

    return new ResultadoCadastroDTO(true, mensagens);
  }

  public List<Profissional> pesquisaProfissionais(FiltroDTO filtro) {
    var especialidade = EspecialidadeProfissional.valueOfNome(filtro.especialidade());
    var plano = PlanoSaudeValido.valueOfNome(filtro.planoSaude());

    return userRepo.findAll(Specification
        .where(ProfissionalSpecification.temCidade(filtro.cidade())
        .and(ProfissionalSpecification.temEspecialidade(especialidade)))
        .and(ProfissionalSpecification.temPlanoSaude(plano)));
  }

  public ResultadoDTO encontrarPorSlug(String slug) throws MediSinaisExcecao {
    Profissional resultado = userRepo.findBySlug(slug);

    if (resultado == null) {
      throw new MediSinaisExcecao(SLUG_NAO_ENCONTRADO);
    }

    return ResultadoDTO.fromProfissional(resultado);
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
