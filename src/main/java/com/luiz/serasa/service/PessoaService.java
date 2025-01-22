package com.luiz.serasa.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.luiz.serasa.dto.PessoaRequestDTO;
import com.luiz.serasa.entity.Pessoa;
import com.luiz.serasa.repository.PessoaRepository;
import com.luiz.serasa.specification.PessoaSpecification;

@Service
public class PessoaService {


    private final PessoaRepository pessoaRepository;
    
    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    public Page<Pessoa> findAllPaginated(String nome, Integer idade, String cep, int page, int size) {
        Specification<Pessoa> spec = Specification
                .where(PessoaSpecification.nomeContains(nome))
                .and(PessoaSpecification.idadeEquals(idade))
                .and(PessoaSpecification.cepEquals(cep));

        Pageable pageable = PageRequest.of(page, size);
        return pessoaRepository.findAll(spec, pageable);
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa save(PessoaRequestDTO pessoa) {
        // Buscar endereço pelo CEP
        Map<String, String> endereco = viaCepService.getEnderecoByCep(pessoa.cep());

        if (endereco == null || endereco.containsKey("erro")) {
            throw new RuntimeException("CEP inválido ou não encontrado.");
        }
        //cria um obj de Pessoa baseada no DTO, e posteriormente preenche os campos de endereço pegos da API viaCep
        Pessoa pessoaSalva = new Pessoa(pessoa);

        
        pessoaSalva.setEstado(endereco.get("uf"));
        pessoaSalva.setCidade(endereco.get("localidade"));
        pessoaSalva.setBairro(endereco.get("bairro"));
        pessoaSalva.setLogradouro(endereco.get("logradouro"));

        
        return pessoaRepository.save(pessoaSalva);
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
            return save(new PessoaRequestDTO(pessoa));
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
    
    public Optional<Pessoa> findByNome(String nome){
    	return pessoaRepository.findByNome(nome);
    }
}
