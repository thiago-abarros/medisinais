package com.mang.medisinais.repositories;

import com.mang.medisinais.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
