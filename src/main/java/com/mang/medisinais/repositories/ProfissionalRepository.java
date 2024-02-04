package com.mang.medisinais.repositories;

import com.mang.medisinais.domain.Profissional;
import jakarta.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProfissionalRepository extends JpaRepository<Profissional, UUID>,
    JpaSpecificationExecutor<Profissional> {

  List<Profissional> findAll(@Nullable Specification<Profissional> spec);

  Profissional findBySlug(String slug);

  Profissional findByEmail(String email);

  Profissional findByCpf(String cpf);

  Profissional findByTelefone(String telefone);
}
