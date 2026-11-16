package br.com.gerenciamento.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioPJTest {

    @Test
    void deveCalcularPagamentoPorProjetos() {
        FuncionarioPJ funcionario = new FuncionarioPJ(
                2, "Carlos", "52998224725", 1500, 3
        );

        assertEquals(4500, funcionario.calcularRemuneracao(), 0.01);
    }

    @Test
    void naoDeveAceitarQuantidadeNegativa() {
        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioPJ(
                        2, "Carlos", "52998224725", 1500, -1
                )
        );
    }
}
