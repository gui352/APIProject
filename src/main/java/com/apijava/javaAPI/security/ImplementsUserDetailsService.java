package com.apijava.javaAPI.security;

import com.apijava.javaAPI.domain.exception.NegocioException;
import com.apijava.javaAPI.domain.model.Pessoa;
import com.apijava.javaAPI.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByUsername(email);
        if (pessoa  == null){
            throw new NegocioException("Usuario ou senha inválido");
        }
        return new User(
                pessoa.getUsername(),
                pessoa.getPassword(),
                true,
                true,
                true,
                true,
                pessoa.getAuthorities()
        );
    }
}