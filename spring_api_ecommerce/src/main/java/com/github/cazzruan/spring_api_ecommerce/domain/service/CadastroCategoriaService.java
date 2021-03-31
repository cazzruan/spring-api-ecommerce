package com.github.cazzruan.spring_api_ecommerce.domain.service;

import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoInvalidoException;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.RecursoNaoEncontradoException;
import com.github.cazzruan.spring_api_ecommerce.domain.exception.ValorDuplicadoException;
import com.github.cazzruan.spring_api_ecommerce.domain.model.Categoria;
import com.github.cazzruan.spring_api_ecommerce.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroCategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CadastroCategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria buscarCategoriaExistente(Long categoriaId) {
        Categoria categoria =  categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", categoriaId)
        );

        return categoria;
    }

    @Transactional
    public Categoria salvarCategoria(Categoria categoria) {
        verificarNomeDuplicado(categoria);
        categoria = categoriaRepository.save(categoria);

        return categoria;
    }

    @Transactional
    public void removerCategoria(Long categoriaId) {
        buscarCategoriaExistente(categoriaId);
        categoriaRepository.deleteById(categoriaId);
    }
    public void verificarNomeDuplicado(Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findByNome(categoria.getNome());

        if (categoriaExistente.isPresent() && !categoriaExistente.get().getId().equals(categoria.getId())) {
            throw new ValorDuplicadoException("Categoria", "nome", categoriaExistente.get().getNome());
        }
    }

    public void verificarCategoriaValida(Long categoriaId) {
        categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoInvalidoException("Categoria", categoriaId));
    }

}
