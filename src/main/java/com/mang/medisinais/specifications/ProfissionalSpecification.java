package com.mang.medisinais.specifications;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ProfissionalSpecification {

  private ProfissionalSpecification() {

  }

  public static Specification<Profissional> temCidade(String cidade) {
    return (root, query, cb) -> cidade == null || cidade.isEmpty() ? cb.conjunction() :
        cb.equal(root.join("endereco", JoinType.INNER).get("cidade"), cidade);
  }

  public static Specification<Profissional> temEspecialidade(
      EspecialidadeProfissional especialidadeProfissional) {

    return (root, query, cb) -> especialidadeProfissional == null ? cb.conjunction() :
        cb.equal(root.get("especialidade"), especialidadeProfissional);
  }

  public static Specification<Profissional> temPlanoSaude(PlanoSaudeValido planoSaude) {
    return (root, query, cb) -> planoSaude == null ? cb.conjunction() :
        cb.equal(root.join("planosAceitos", JoinType.INNER).get("nome"), planoSaude);
  }

  public static Specification<Profissional> temNome(String nome) {
    return (root, query, cb) -> {
      if(nome == null || nome.isEmpty()) {
        return cb.conjunction();
      } else {
        String nomeMinusculo = "%" + nome.toLowerCase() + "%";
        return cb.like(cb.lower(root.get("nome")), nomeMinusculo);
      }
    };
  }

}
