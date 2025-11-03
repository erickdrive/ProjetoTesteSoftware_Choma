package com.teste.spring.teste;

import com.teste.spring.teste.dto.ClienteDto;
import com.teste.spring.teste.mapa.ClienteMapa;
import com.teste.spring.teste.model.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteMapaTest {
    @Test
    void testToEntity() {
        ClienteDto dto = new ClienteDto();
        dto.setId(1L);
        dto.setNome("Erick");
        dto.setEmail("erick@email.com");
        dto.setTelefone("123456789");

        Cliente entity = ClienteMapa.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNome(), entity.getNome());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getTelefone(), entity.getTelefone());
    }

    @Test
    void testCopyToEntity() {
        ClienteDto dto = new ClienteDto();
        dto.setId(1L);
        dto.setNome("Helder");
        dto.setEmail("helder@email.com");
        dto.setTelefone("123456789");

        Cliente cliente = new Cliente();

        Cliente entity = ClienteMapa.copyToEntity(dto,cliente);

        assertEquals(dto.getNome(), entity.getNome());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getTelefone(), entity.getTelefone());
    }

}
