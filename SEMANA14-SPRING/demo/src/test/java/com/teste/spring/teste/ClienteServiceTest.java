package com.teste.spring.teste;


import com.teste.spring.teste.model.Cliente;
import com.teste.spring.teste.exception.*;
import com.teste.spring.teste.repository.ClienteRepository;
import com.teste.spring.teste.service.ClienteService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    ClienteRepository repo;

    @InjectMocks
    ClienteService service;

    @Test
    void criar_deveLancarSeEmailJaExiste() {
        Cliente c = new Cliente();
        c.setNome("João");
        c.setEmail("j@ex.com");
        when(repo.existsByEmail("j@ex.com")).thenReturn(true);

        assertThatThrownBy(() -> service.criar(c))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Email já cadastrado");
        verify(repo, never()).save(any());
    }

    @Test
    void salvarCliente(){
    Cliente cliente = new Cliente();
    cliente.setNome("João");
    cliente.setEmail("joao@exemplo.com");
    cliente.setTelefone("12345");

    when(repo.existsByEmail("joao@exemplo.com")).thenReturn(false);
    when(repo.save(any(Cliente.class))).thenAnswer(invoc -> {
        Cliente c = invoc.getArgument(0);
        c.setId(1L);
        return c;
    });
    Cliente salvo = service.criar(cliente);

    verify(repo).save(cliente);
    assertThat(salvo.getId()).isEqualTo(1L);
    assertThat(salvo.getNome()).isEqualTo("João");
    assertThat(salvo.getEmail()).isEqualTo("joao@exemplo.com");
    }

    @Test
    void excluirCliente(){
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Novo");
        cliente.setEmail("novo@ex.com");
        cliente.setTelefone("22");

        when(repo.findById(1L)).thenReturn(Optional.of(cliente));
        service.excluir(1L);
        verify(repo).delete(cliente);
    }

    @Test
    void atualizar_deveAtualizarCamposBasicos() {
        Cliente antigo = new Cliente();
        antigo.setId(1L);
        antigo.setNome("Antigo");
        antigo.setEmail("a@ex.com");
        antigo.setTelefone("11");

        when(repo.findById(1L)).thenReturn(Optional.of(antigo));
        when(repo.findByEmail("novo@ex.com")).thenReturn(Optional.of(antigo)); // mesmo cliente
        when(repo.existsByEmail("novo@ex.com")).thenReturn(true);
        when(repo.save(any(Cliente.class))).thenAnswer(i -> i.getArgument(0));

        Cliente dados = new Cliente();
        dados.setNome("Novo");
        dados.setEmail("novo@ex.com");
        dados.setTelefone("22");

        Cliente atualizado = service.atualizar(1L, dados);

        assertThat(atualizado.getNome()).isEqualTo("Novo");
        assertThat(atualizado.getEmail()).isEqualTo("novo@ex.com");
        assertThat(atualizado.getTelefone()).isEqualTo("22");
    }

    @Test
    void businessExceptionCriarCliente(){
        Cliente existente = new Cliente();
        existente.setId(1L);
        existente.setNome("Helder");
        existente.setEmail("helder@ex.com");
        existente.setTelefone("11");

        Cliente outroCliente = new Cliente();
        outroCliente.setId(2L);
        outroCliente.setNome("Maria");
        outroCliente.setEmail("helder@ex.com");
        outroCliente.setTelefone("22");

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.existsByEmail("helder@ex.com")).thenReturn(true);
        when(repo.findByEmail("helder@ex.com")).thenReturn(Optional.of(outroCliente));

        assertThatThrownBy(() -> service.atualizar(1L, outroCliente))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Email já cadastrado");

        verify(repo, never()).save(any());
    }

}