package com.mang.medisinais.specifications;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.enums.EspecialidadeProfissional;
import com.mang.medisinais.domain.enums.PlanoSaudeValido;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ProfissionalSpecification {

    public static Specification<Profissional> temCidade(String cidade) {
        return ((root, query, cb) -> cidade == null || cidade.isEmpty() ? cb.conjunction() :
                cb.equal(root.join("enderecos", JoinType.INNER).get("cidade"), cidade));
    }

    public static Specification<Profissional> temEspecialidade( EspecialidadeProfissional especialidadeProfissional) {

        return ((root, query, cb) -> especialidadeProfissional == null ? cb.conjunction() :
                cb.equal(root.get("especialidade"), especialidadeProfissional));
    }

    public static Specification<Profissional> temPlanoSaude(String healthInsurance) {
        long id = PlanoSaudeValido.valueOfNome(healthInsurance);

        return ((root, query, cb) -> healthInsurance == null || healthInsurance.isEmpty()  ? cb.conjunction() :
                cb.equal(root.join("planosAceitos", JoinType.INNER).get("id"), id));
    }

}
