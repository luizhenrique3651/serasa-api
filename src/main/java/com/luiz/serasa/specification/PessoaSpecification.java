package com.luiz.serasa.specification;

import com.luiz.serasa.entity.Pessoa;
import org.springframework.data.jpa.domain.Specification;

public class PessoaSpecification {

    public static Specification<Pessoa> nomeContains(String nome) {
        return (root, query, criteriaBuilder) ->
                nome == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Pessoa> idadeEquals(Integer idade) {
        return (root, query, criteriaBuilder) ->
                idade == null ? null : criteriaBuilder.equal(root.get("idade"), idade);
    }

    public static Specification<Pessoa> cepEquals(String cep) {
        return (root, query, criteriaBuilder) ->
                cep == null ? null : criteriaBuilder.equal(root.get("cep"), cep);
    }
}
