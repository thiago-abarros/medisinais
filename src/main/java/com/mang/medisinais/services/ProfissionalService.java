package com.mang.medisinais.services;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.dto.FiltroDTO;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
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
    return userRepo.findAll(Specification
            .where(ProfissionalSpecification.temCidade(filtro.cidade())
            .and(ProfissionalSpecification.temEspecialidade(EspecialidadeProfissional.valueOfNome(filtro.especialidade()))))
            .and(ProfissionalSpecification.temPlanoSaude(filtro.planoSaude())));
  }

}
