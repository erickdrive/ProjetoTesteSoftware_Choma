package com.teste.spring.teste;

import com.teste.spring.teste.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotFoundExceptionTest {
    @Test
    void deveConterMensagemCorreta() {
        String mensagem = "Cliente não encontrado";
        NotFoundException ex = new NotFoundException(mensagem);

        assertEquals(mensagem, ex.getMessage());
    }
}
