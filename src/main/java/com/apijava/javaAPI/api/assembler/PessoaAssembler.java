package com.apijava.javaAPI.api.assembler;

import com.apijava.javaAPI.api.model.PessoaDTO;
import com.apijava.javaAPI.api.model.LoginDTO;
import com.apijava.javaAPI.api.model.input.PessoaInputDTO;
import com.apijava.javaAPI.domain.model.Pessoa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PessoaAssembler {

    private ModelMapper modelMapper;

    public Pessoa toEntity(PessoaInputDTO pessoaInputDTO){
        return modelMapper.map(pessoaInputDTO, Pessoa.class);
    }

    public List<PessoaDTO> toCollectionModel(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public PessoaDTO toModel(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public Pessoa toEntityLogin(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Pessoa.class);
    }


}
