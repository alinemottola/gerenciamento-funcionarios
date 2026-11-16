package br.com.gerenciamento.view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioViewTest {

    @Test
    void deveExibirMenuPrincipal() {

        InputStream entradaOriginal = System.in;
        PrintStream saidaOriginal = System.out;

        ByteArrayInputStream entrada =
                new ByteArrayInputStream(
                        "0\n".getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream saida =
                new ByteArrayOutputStream();

        try {
            System.setIn(entrada);
            System.setOut(new PrintStream(saida));

            FuncionarioView view = new FuncionarioView();
            view.iniciar();

            String resultado = saida.toString();

            assertTrue(resultado.contains(
                    "GERENCIAMENTO DE FUNCIONÁRIOS"));

            assertTrue(resultado.contains(
                    "Cadastrar funcionário"));

            assertTrue(resultado.contains(
                    "Encerrando sistema"));

        } finally {
            System.setIn(entradaOriginal);
            System.setOut(saidaOriginal);
        }
    }
}