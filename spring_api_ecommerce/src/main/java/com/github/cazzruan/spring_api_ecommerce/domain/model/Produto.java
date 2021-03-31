package com.github.cazzruan.spring_api_ecommerce.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataUltimaAtualizacao = LocalDateTime.now();

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne()
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

}
