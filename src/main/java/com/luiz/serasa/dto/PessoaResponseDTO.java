package com.luiz.serasa.dto;

import com.luiz.serasa.entity.Pessoa;
import com.luiz.serasa.service.ScoreDescricaoService;

public record PessoaResponseDTO(Long id, String nome, Integer idade, String cep, String estado, String cidade,
                                String bairro, String logradouro, String telefone, Integer score, String descricaoScore) {

    public PessoaResponseDTO(Pessoa pessoa, ScoreDescricaoService scoreDescricaoService) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getCep(), pessoa.getEstado(),
             pessoa.getCidade(), pessoa.getBairro(), pessoa.getLogradouro(), pessoa.getTelefone(),
             pessoa.getScore(), scoreDescricaoService.obterDescricaoScore(pessoa.getScore()));
    }
}
