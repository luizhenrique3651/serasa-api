package com.luiz.serasa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.serasa.entity.Pessoa;
import com.luiz.serasa.repository.PessoaRepository;

@Service
public class PessoaService {


    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa update(Long id, Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoa pessoa = pessoaExistente.get();
            pessoa.setNome(pessoaAtualizada.getNome() != null ? pessoaAtualizada.getNome() : pessoa.getNome());
            pessoa.setIdade(pessoaAtualizada.getIdade() != null ? pessoaAtualizada.getIdade() : pessoa.getIdade());
            pessoa.setCep(pessoaAtualizada.getCep() != null ? pessoaAtualizada.getCep() : pessoa.getCep());
            pessoa.setEstado(pessoaAtualizada.getEstado() != null ? pessoaAtualizada.getEstado() : pessoa.getEstado());
            pessoa.setCidade(pessoaAtualizada.getCidade() != null ? pessoaAtualizada.getCidade() : pessoa.getCidade());
            pessoa.setBairro(pessoaAtualizada.getBairro() != null ? pessoaAtualizada.getBairro() : pessoa.getBairro());
            pessoa.setLogradouro(pessoaAtualizada.getLogradouro() != null ? pessoaAtualizada.getLogradouro() : pessoa.getLogradouro());
            pessoa.setTelefone(pessoaAtualizada.getTelefone() != null ? pessoaAtualizada.getTelefone() : pessoa.getTelefone());
            pessoa.setScore(pessoaAtualizada.getScore() != null ? pessoaAtualizada.getScore() : pessoa.getScore());
            return pessoaRepository.save(pessoa);
        } else {
            throw new RuntimeException("Pessoa não encontrada com o ID: " + id);
        }
    }


    public void delete(Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pessoa não encontrada com o ID: " + id);
        }
    }
}
