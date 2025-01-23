package com.luiz.serasa.service;

import com.luiz.serasa.dto.PessoaRequestDTO;
import com.luiz.serasa.entity.Pessoa;
import com.luiz.serasa.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;
  
    @Mock
    private ScoreDescricaoService scoreDescricaoService;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
    }

 

  

    @Test
    void testDeletePessoa_Success() {
        // Arrange
        Long id = 1L;

        // Mock repository existsById and deleteById
        when(pessoaRepository.existsById(id)).thenReturn(true);

        // Act
        pessoaService.delete(id);

        // Assert
        verify(pessoaRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindByNome_Success() {
        // Arrange
        String nome = "Maria";
        Pessoa pessoa = new Pessoa(1L, "Maria", 30, "12345678", "SP", "São Paulo", "Centro", "Rua Teste", "987654321", 500);
        when(pessoaRepository.findByNome(nome)).thenReturn(Optional.of(pessoa));

        // Act
        Optional<Pessoa> result = pessoaService.findByNome(nome);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getNome());
    }

    @Test
    void testFindByNome_NotFound() {
        // Arrange
        String nome = "João";
        when(pessoaRepository.findByNome(nome)).thenReturn(Optional.empty());

        // Act
        Optional<Pessoa> result = pessoaService.findByNome(nome);

        // Assert
        assertFalse(result.isPresent());
    }

 
}
