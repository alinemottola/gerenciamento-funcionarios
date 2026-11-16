package br.com.gerenciamento.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioJaCadastradoExceptionTest {

    @Test
    void deveGuardarMensagemDeErro() {
        FuncionarioJaCadastradoException erro =
                new FuncionarioJaCadastradoException("Funcionário já cadastrado");

        assertEquals("Funcionário já cadastrado", erro.getMessage());
    }
}