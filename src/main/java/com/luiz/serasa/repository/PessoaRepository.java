package com.luiz.serasa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.luiz.serasa.entity.Pessoa;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

	Optional<Pessoa> findByNome(String nome);
}
