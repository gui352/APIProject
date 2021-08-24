package com.apijava.javaAPI.domain.repository;

import com.apijava.javaAPI.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    @Query("select p from Pessoa p where p.email = ?1")
    Pessoa findByUsername(String email);

    @Query("SELECT p FROM Pessoa p WHERE p.email = ?1")
    Optional<Pessoa> findByEmail(String email);

}
