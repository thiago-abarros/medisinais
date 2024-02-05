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
import com.mang.medisinais.dto.ResultadoCadastroDTO;
import com.mang.medisinais.dto.ResultadoDTO;
import com.mang.medisinais.infra.MediSinaisExcecao;
import com.mang.medisinais.repositories.EnderecoRepository;
import com.mang.medisinais.repositories.PlanoSaudeRepository;
import com.mang.medisinais.repositories.ProfissionalRepository;
import com.mang.medisinais.specifications.ProfissionalSpecification;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

  private static final String EMAIL_PROFISSIONAL_NAO_ENCONTRADO = "Email não encontrado.";
  private static final String SENHA_INCORRETA = "Senha incorreta.";
  private static final String PROFISSIONAL_NAO_ENCONTRADO = "Profissional não encontrado.";

  private final BCryptPasswordEncoder bcrypt;
  private final ProfissionalRepository userRepo;
  private final EnderecoRepository enderecoRepo;
  private final PlanoSaudeRepository planoRepo;
  private final ValidadorService validador;

  public ResultadoCadastroDTO criarProfissional(CadastroProfissionalDTO dados) {
    Map<String, String> mensagens = validador.validarProfissional(dados);

    if (!mensagens.isEmpty()) {
      return new ResultadoCadastroDTO(false, mensagens);
    }

    String senhaHash = bcrypt.encode(dados.senha());
    List<PlanoSaude> planos = Optional.ofNullable(dados.planosAceitos())
            .map(list -> list.stream().map(PlanoSaude::new).toList())
            .orElse(Collections.emptyList());

    Profissional novoProfissional = new Profissional(dados, senhaHash, planos);
    Endereco novoEndereco = new Endereco(dados.endereco(), novoProfissional);

    this.userRepo.save(novoProfissional);
    this.enderecoRepo.save(novoEndereco);

    return new ResultadoCadastroDTO(true, mensagens);
  }

  public OperacaoDTO alterarProfissional(UUID idProfissional, AtualizarProfissionalDTO dados)
      throws MediSinaisExcecao {
    // TODO Validação

    Profissional profissional = encontrarProfissionalPorId(idProfissional);
    Endereco endereco = new Endereco(dados.endereco(), profissional);
    String senhaHash = bcrypt.encode(dados.senha());

    profissional.setNome(dados.nome());
    profissional.setFoto(dados.foto());
    profissional.setEmail(dados.email());
    profissional.setPlanosAceitos(dados.planosAceitos());
    profissional.setSenha(senhaHash);

    this.userRepo.save(profissional);
    this.enderecoRepo.save(endereco);

    return new OperacaoDTO(true, "Dados alterados com sucesso", idProfissional);
  }

  public List<ResultadoDTO> pesquisaProfissionais(FiltroDTO filtro) {
    var especialidade = EspecialidadeProfissional.valueOfNome(filtro.especialidade());
    var plano = PlanoSaudeValido.valueOfNome(filtro.planoSaude());

    List<Profissional> profissional = userRepo.findAll(Specification
            .where(ProfissionalSpecification.temCidade(filtro.cidade())
            .and(ProfissionalSpecification.temEspecialidade(especialidade)))
            .and(ProfissionalSpecification.temPlanoSaude(plano)));

      return profissional.stream().map(ResultadoDTO::fromProfissional).toList();
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
