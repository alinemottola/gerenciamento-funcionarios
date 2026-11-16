package br.com.gerenciamento.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioNaoEncontradoExceptionTest {

    @Test
    void deveGuardarMensagemDeErro() {
        FuncionarioNaoEncontradoException erro =
                new FuncionarioNaoEncontradoException("Funcionário não encontrado");

        assertEquals("Funcionário não encontrado", erro.getMessage());
    }
}