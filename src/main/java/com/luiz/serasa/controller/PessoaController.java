package com.luiz.serasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.luiz.serasa.service.ScoreDescricaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private ScoreDescricaoService scoreDescricaoService;

    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Carrega todos os objetos de Pessoa do banco.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso")})
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<PessoaResponseDTO> loadAll() {
    	return pessoaService.findAll().stream()
                .map(pessoa -> new PessoaResponseDTO(pessoa, scoreDescricaoService))
                .toList();
    }
    

    @SecurityRequirement(name = "BearerAuth")
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


    @SecurityRequirement(name = "BearerAuth")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pessoa salva com sucesso")})
    @Operation(description = "Salva uma nova Pessoa no banco.")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> savePessoa(@RequestBody PessoaRequestDTO pessoa) {
        pessoaService.save(pessoa);
        return new ResponseEntity<>("Pessoa salva com sucesso.", HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Atualiza uma Pessoa no banco.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Pessoa não encontrada")
    })
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePessoa(@PathVariable Long id, @RequestBody PessoaRequestDTO pessoaAtualizada) {
        try {
            pessoaService.update(id, new Pessoa(pessoaAtualizada));
            return new ResponseEntity<>("Pessoa atualizada com sucesso.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Pessoa não encontrada.", HttpStatus.BAD_REQUEST);
        }
    }


    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Deleta uma Pessoa pelo ID.")

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Pessoa não encontrada")
    })
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id) {
        try {
            pessoaService.delete(id);
            return new ResponseEntity<>("Pessoa deletada com sucesso.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Pessoa não encontrada.", HttpStatus.BAD_REQUEST);
        }
    }

    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Carrega uma Pessoa pelo nome.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/byNome")
    public ResponseEntity<PessoaResponseDTO> loadByNome(@RequestParam String nome) {
        return pessoaService.findByNome(nome)
            .map(pessoa -> new ResponseEntity<>(new PessoaResponseDTO(pessoa, scoreDescricaoService), HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
