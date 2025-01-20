package com.luiz.serasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luiz.serasa.entity.Pessoa;
import com.luiz.serasa.repository.PessoaRepository;
import com.luiz.serasa.service.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Operation(description = "Carrega todos os objetos de Pessoa do banco.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Pessoa> loadAll() {
        return pessoaService.findAll();
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pessoa salva com sucesso")})
    @Operation(description = "Salva uma nova Pessoa no banco.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void savePessoa(@RequestBody Pessoa pessoa) {
        pessoaService.save(pessoa);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Pessoa não encontrada")
    })
    @Operation(description = "Atualiza uma Pessoa no banco.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public void updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        pessoaService.update(id, pessoaAtualizada);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Pessoa não encontrada")
    })
    @Operation(description = "Deleta uma Pessoa pelo ID.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable Long id) {
        pessoaService.delete(id);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @Operation(description = "Carrega uma Pessoa pelo nome.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/byNome")
    public Pessoa loadByNome(@RequestParam String nome) {
        return pessoaRepository.findByNome(nome)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }
}
