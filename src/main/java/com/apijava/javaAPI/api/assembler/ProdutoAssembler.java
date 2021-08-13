package com.apijava.javaAPI.api.assembler;

import com.apijava.javaAPI.api.model.ProdutoDTO;
import com.apijava.javaAPI.api.model.input.ProdutoInputDTO;
import com.apijava.javaAPI.domain.model.Produto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProdutoAssembler {

    private ModelMapper modelMapper;

    public ProdutoDTO toModel(Produto produto){
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toColletionModel(List<Produto> produtos){
        return produtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Produto toEntity(ProdutoInputDTO produtoInputDTO){
        return modelMapper.map(produtoInputDTO, Produto.class);
    }
}
