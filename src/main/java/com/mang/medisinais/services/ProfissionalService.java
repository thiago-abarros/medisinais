package com.mang.medisinais.services;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.dto.FiltroDTO;
import com.mang.medisinais.domain.dto.ResultadoDTO;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import com.mang.medisinais.repositories.ProfissionalRepository;
import com.mang.medisinais.specifications.ProfissionalSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

  private final ProfissionalRepository userRepo;

  public List<Profissional> pesquisaProfissionais(FiltroDTO filtro) {
    var especialidade = EspecialidadeProfissional.valueOfNome(filtro.especialidade());
    var plano = PlanoSaudeValido.valueOfNome(filtro.planoSaude());

    return userRepo.findAll(Specification
            .where(ProfissionalSpecification.temCidade(filtro.cidade())
            .and(ProfissionalSpecification.temEspecialidade(especialidade)))
            .and(ProfissionalSpecification.temPlanoSaude(plano)));
  }

  public ResultadoDTO encontrarPorSlug(String slug) {
    Profissional resultado = userRepo.findBySlug(slug);

    if(resultado == null) {
      return null;
    }

    return ResultadoDTO.fromProfissional(userRepo.findBySlug(slug));
  }

}
