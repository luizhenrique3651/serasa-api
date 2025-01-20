package com.luiz.serasa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pessoa {
	
	@Id
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
	
}
