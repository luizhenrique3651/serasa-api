package com.luiz.serasa.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Pessoa {

	Long id;
	
	String nome;
	
	Integer idade;
	
	String cep;
	
	String estado;
	
	String cidade;
	
	String bairro;
	
	String logradouro;
	
	String telefone;
	
	Integer score;
	
}
