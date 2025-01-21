package com.luiz.serasa.entity;

import com.luiz.serasa.dto.PessoaRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	@Column
	String nome;
	@Column
	Integer idade;
	@Column
	String cep;
	@Column
	String estado;
	@Column
	String cidade;
	@Column
	String bairro;
	@Column
	String logradouro;
	@Column
	String telefone;
	@Column
	Integer score;
	
	public Pessoa(PessoaRequestDTO data) {
		this.nome = data.nome();
		this.idade = data.idade();
		this.cep = data.cep();
		this.telefone = data.telefone();
		this.score = data.score();
	}
	
}
