package com.luiz.serasa.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ViaCepService {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public Map<String, String> getEnderecoByCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(VIACEP_URL, Map.class, cep);
    }
}
