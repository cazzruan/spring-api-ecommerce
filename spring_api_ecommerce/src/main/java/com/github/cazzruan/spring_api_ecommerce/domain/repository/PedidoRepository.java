package com.github.cazzruan.spring_api_ecommerce.domain.repository;

import com.github.cazzruan.spring_api_ecommerce.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
