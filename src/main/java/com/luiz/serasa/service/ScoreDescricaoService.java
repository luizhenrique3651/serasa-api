package com.luiz.serasa.service;

import org.springframework.stereotype.Service;

@Service
public class ScoreDescricaoService {

    public String obterDescricaoScore(Integer score) {
        if (score == null) {
            return "Score inválido";
        }
        if (score >= 0 && score <= 200) {
            return "Insuficiente";
        } else if (score >= 201 && score <= 500) {
            return "Inaceitável";
        } else if (score >= 501 && score <= 700) {
            return "Aceitável";
        } else if (score >= 701 && score <= 1000) {
            return "Recomendável";
        } else {
            return "Score inválido";
        }
    }
}
