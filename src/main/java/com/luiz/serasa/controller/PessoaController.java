package com.luiz.serasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.luiz.serasa.dto.PessoaRequestDTO;
import com.luiz.serasa.dto.PessoaResponseDTO;
import com.luiz.serasa.entity.Pessoa;
import com.luiz.serasa.service.PessoaService;
import com.luiz.serasa.service.ViaCepService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private PessoaService pessoaService;

    @Operation(description = "Carrega todos os objetos de Pessoa do banco.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<PessoaResponseDTO> loadAll() {
        return pessoaService.findAll().stream().map(PessoaResponseDTO::new).toList();
    }
    
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso")})
    @Operation(description = "Retorna uma lista de pessoas paginada e com filtros opcionais (nome, idade, cep).")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/paginado")
    public Page<Pessoa> getPessoasPaginadas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer idade,
            @RequestParam(required = false) String cep,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return pessoaService.findAllPaginated(nome, idade, cep, page, size);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pessoa salva com sucesso")})
    @Operation(description = "Salva uma nova Pessoa no banco.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void savePessoa(@RequestBody PessoaRequestDTO pessoa) {
    	//implementação da busca de endereço pelo cep está direto no service
        pessoaService.save(pessoa);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Pessoa não encontrada")
    })
    @Operation(description = "Atualiza uma Pessoa no banco.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public void updatePessoa(@PathVariable Long id, @RequestBody PessoaRequestDTO pessoaAtualizada) {    	
       pessoaService.update(id, new Pessoa(pessoaAtualizada));
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
        return pessoaService.findByNome(nome)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }
}
