package com.mang.medisinais.repositories;

import com.mang.medisinais.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Profissional, Long> {}
