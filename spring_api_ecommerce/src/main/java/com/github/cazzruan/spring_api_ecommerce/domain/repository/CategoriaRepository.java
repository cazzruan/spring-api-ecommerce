package com.github.cazzruan.spring_api_ecommerce.domain.repository;

import com.github.cazzruan.spring_api_ecommerce.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nomeCategoria);

}
