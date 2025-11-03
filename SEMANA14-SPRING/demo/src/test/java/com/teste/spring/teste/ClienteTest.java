package com.teste.spring.teste;

import com.teste.spring.teste.model.Cliente;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;



public class ClienteTest {

    @Test
    public void testDataCriacao() {
        Cliente cliente = new Cliente();
        System.out.println(cliente.getCriadoEm());
    }

    @Test
    public void testSetDataCriacao() {
        Cliente cliente = new Cliente();
        cliente.setCriadoEm(LocalDateTime.now());
    }
}
