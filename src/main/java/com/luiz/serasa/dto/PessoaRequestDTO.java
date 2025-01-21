package com.luiz.serasa.dto;

import com.luiz.serasa.entity.Pessoa;

public record PessoaRequestDTO(String nome, Integer idade, String cep, String telefone, Integer score) {

	public PessoaRequestDTO(Pessoa pessoa) {
		this(pessoa.getNome(), pessoa.getIdade(), pessoa.getCep(),  pessoa.getTelefone(),
				pessoa.getScore());
	}
}
