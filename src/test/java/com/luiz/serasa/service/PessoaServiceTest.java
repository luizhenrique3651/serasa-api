package com.luiz.serasa.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.luiz.serasa.dto.PessoaRequestDTO;
import com.luiz.serasa.entity.Pessoa;
import com.luiz.serasa.repository.PessoaRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ViaCepService viaCepService;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;
    private PessoaRequestDTO pessoaRequestDTO;

    @BeforeEach
    public void setUp() {
        pessoa = new Pessoa(1L, "João", 30, "12345678", "PE", "Recife", "Bairro", "Rua", "123456789", 650);
        pessoaRequestDTO = new PessoaRequestDTO("João", 30, "12345678", "123456789", 650);
    }

    @Test
    public void testSavePessoa() {
        when(viaCepService.getEnderecoByCep(any(String.class))).thenReturn(Map.of("uf", "PE", "localidade", "Recife", "bairro", "Bairro", "logradouro", "Rua"));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa savedPessoa = pessoaService.save(pessoaRequestDTO);

        assertNotNull(savedPessoa);
        assertEquals("João", savedPessoa.getNome());
        assertEquals("PE", savedPessoa.getEstado());
    }



    @Test
    public void testDeletePessoa() {
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);

        pessoaService.delete(1L);

        verify(pessoaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindAll() {
        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        var pessoas = pessoaService.findAll();

        assertNotNull(pessoas);
        assertFalse(pessoas.isEmpty());
        assertEquals(1, pessoas.size());
    }
}
