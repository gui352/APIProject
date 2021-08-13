package com.apijava.javaAPI.domain.service;

import com.apijava.javaAPI.api.assembler.PessoaAssembler;
import com.apijava.javaAPI.api.model.PessoaDTO;
import com.apijava.javaAPI.domain.exception.NegocioException;
import com.apijava.javaAPI.domain.model.Perfil;
import com.apijava.javaAPI.domain.model.Pessoa;
import com.apijava.javaAPI.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private PessoaAssembler pessoaAssembler;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa){
                boolean emailValidation = pessoaRepository.findByEmail(
                pessoa.getEmail()).isPresent();
        if (emailValidation){
            throw new NegocioException(
                    "Ja existe uma  pessoa com esse e-mail cadastrado.");
        }
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscar(Long codigo){
        return pessoaRepository.findById(codigo)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public List<PessoaDTO> listar(){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }

    public ResponseEntity<PessoaDTO> editar(Long codigo, Pessoa pessoa) {
        if (!pessoaRepository.existsById(codigo)){
            throw new NegocioException("Pessoa não encontrada");
        }
        Pessoa pessoa1 = this.buscar(codigo);
        pessoa.setCodigo(codigo);
        pessoa.setPerfil(pessoa1.getPerfil());
        pessoa1 = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa1));
    }

    public Pessoa editarPerfil(Long codigo) {
        if (!pessoaRepository.existsById(codigo)){
            throw new NegocioException("Pessoa não encontrada");
        }
        Pessoa pessoa1 = this.buscar(codigo);
        pessoa1.setCodigo(codigo);
        pessoa1.setPerfil(Perfil.ROLE_ADMIN);
        pessoa1 = pessoaRepository.save(pessoa1);
        return pessoa1;
    }


    @Transactional
    public void deletar(Long codigo){
        pessoaRepository.deleteById(codigo);
    }
}

