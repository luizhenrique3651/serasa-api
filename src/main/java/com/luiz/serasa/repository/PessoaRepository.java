package com.luiz.serasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luiz.serasa.entity.Pessoa;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Long>{

}
