package br.com.gerenciamento.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioComissionadoTest {

    @Test
    void deveCalcularSalarioComComissao() {
        FuncionarioComissionado funcionario =
                new FuncionarioComissionado(
                        3, "Maria", "52998224725",
                        2000, 10000, 10
                );

        assertEquals(3000, funcionario.calcularRemuneracao(), 0.01);
    }

    @Test
    void naoDeveAceitarPercentualInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioComissionado(
                        3, "Maria", "52998224725",
                        2000, 10000, 150
                )
        );
    }
}