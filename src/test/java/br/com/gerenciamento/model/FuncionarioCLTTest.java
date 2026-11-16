package br.com.gerenciamento.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioCLTTest {

    @Test
    void deveCalcularSalarioComBeneficios() {
        FuncionarioCLT funcionario = new FuncionarioCLT(
                1, "Ana", "52998224725", 3000, 500
        );

        assertEquals(3500, funcionario.calcularRemuneracao(), 0.01);
    }

    @Test
    void naoDeveAceitarBeneficiosNegativos() {
        assertThrows(IllegalArgumentException.class, () ->
                new FuncionarioCLT(
                        1, "Ana", "52998224725", 3000, -500
                )
        );
    }
}
