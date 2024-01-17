package com.mang.medisinais.repositories;

import com.mang.medisinais.domain.Profissional;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>, JpaSpecificationExecutor<Profissional> {

    List<Profissional> findAll(@Nullable Specification<Profissional> spec);

}
