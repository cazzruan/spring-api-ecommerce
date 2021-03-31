package com.github.cazzruan.spring_api_ecommerce.domain.repository;

import com.github.cazzruan.spring_api_ecommerce.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String email);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByTelefone(String email);

}
