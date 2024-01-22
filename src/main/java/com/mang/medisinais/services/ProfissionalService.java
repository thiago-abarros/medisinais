package com.mang.medisinais.services;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import com.mang.medisinais.dto.FiltroDTO;
import com.mang.medisinais.dto.LoginDTO;
import com.mang.medisinais.dto.ProfissionalDTO;
import com.mang.medisinais.dto.ResultadoDTO;
import com.mang.medisinais.infra.MediSinaisExcecao;
import com.mang.medisinais.repositories.ProfissionalRepository;
import com.mang.medisinais.specifications.ProfissionalSpecification;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

  private static final String EMAIL_PROFISSIONAL_NAO_ENCONTRADO = "Email não encontrado.";
  private static final String SENHA_INCORRETA = "Senha incorreta.";
  private static final String SLUG_NAO_ENCONTRADO = "Profissional não encontrado";

  private final ProfissionalRepository userRepo;

  public ProfissionalDTO criarProfissional(ProfissionalDTO dados) {

    if (Boolean.TRUE.equals(validarCadastro(dados))) {
      Profissional novoProfissional = new Profissional(dados);
      this.userRepo.save(novoProfissional);
    }

    return dados;
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

  public Profissional autenticarProfissional(LoginDTO dadosLogin) throws MediSinaisExcecao {
    Profissional profissional = this.userRepo.findByEmail(dadosLogin.email());

    if (profissional == null) {
      throw new MediSinaisExcecao(EMAIL_PROFISSIONAL_NAO_ENCONTRADO);
    }
    if (!Objects.equals(profissional.getSenha(), dadosLogin.senha())) {
      throw new MediSinaisExcecao(SENHA_INCORRETA);
    }

    return profissional;
  }

  private Boolean validarCadastro(ProfissionalDTO dados) {
    return !(dados.nome().isEmpty() || dados.cpf().isEmpty() || dados.email().isEmpty()
        || dados.telefone().isEmpty() || dados.especialidade() == null);
  }
}
