package com.apijava.javaAPI.domain.service;

import com.apijava.javaAPI.api.assembler.ProdutoAssembler;
import com.apijava.javaAPI.api.model.ProdutoDTO;
import com.apijava.javaAPI.domain.exception.NegocioException;
import com.apijava.javaAPI.domain.model.Produto;
import com.apijava.javaAPI.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private ProdutoAssembler produtoAssembler;

    @Transactional
    public Produto cadastrar(Produto produto){
                boolean productValidation = produtoRepository.findByName(
                produto.getProduto()).isPresent();
        if (!!productValidation){
            throw new NegocioException(
                    "Ja existe um produto com esse nome cadastrado.");
        }
       return produtoRepository.save(produto);
    }

    public Produto buscar(Long codigo){
        return produtoRepository.findById(codigo)
                .orElseThrow(() -> new NegocioException("Produto não encontrado."));
    }

    public List<ProdutoDTO> listar(){
        return produtoAssembler.toColletionModel(produtoRepository.findAll());
    }

    public ResponseEntity<ProdutoDTO> editar(Long codigo, Produto produto) {
        if(!produtoRepository.existsById(codigo)){
            throw new NegocioException("Produto inexistente");
        }
        Produto produto1 = this.buscar(codigo);
        produto.setCodigo(codigo);
        produto = produtoRepository.save(produto);

        return ResponseEntity.ok(produtoAssembler.toModel(produto));
    }

    @Transactional
    public void deletar(Long codigo){
        produtoRepository.deleteById(codigo);
    }
}

