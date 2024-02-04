package com.mang.medisinais.repositories;

import com.mang.medisinais.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

}
