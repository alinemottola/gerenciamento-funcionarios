package br.com.gerenciamento.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FuncionarioTest {

    @Test
    void deveRetornarCodigodoFuncionario() {

        Funcionario funcionario = new Funcionario(
                1, "Ana Maria", "12345678909", 3000.00
        ){
            @Override public double calcularRemuneracao() {
                return 3000.00;
            }
        };

        assertEquals(1, funcionario.getCodigo());
    }

    @Test
    void naoDeveAceitarSalarioNegativo() {

        assertThrows(IllegalArgumentException.class, () -> {

            new Funcionario(
                    2, "Carlos Silva", "12345678909", -2000.00
            ){
                @Override public double calcularRemuneracao() {
                    return 0;
                }
            };
        });
    }

}
