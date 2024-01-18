package com.mang.medisinais.repositories;

import com.mang.medisinais.domain.Profissional;
import com.mang.medisinais.domain.dto.ResultadoDTO;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>, JpaSpecificationExecutor<Profissional> {

    List<Profissional> findAll(@Nullable Specification<Profissional> spec);

    Profissional findBySlug(@Param(("slug")) String slug);

}
