package br.com.gerenciamento.repository;

import br.com.gerenciamento.model.Funcionario;
import br.com.gerenciamento.model.FuncionarioCLT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioRepositoryTest {

    @Test
    void deveSalvarFuncionario() {
        FuncionarioRepository repository = new FuncionarioRepository();

        Funcionario funcionario = new FuncionarioCLT(
                1, "Ana", "52998224725", 3000, 500
        );

        repository.salvar(funcionario);

        assertEquals(1, repository.listarTodos().size());
    }

    @Test
    void deveBuscarFuncionarioPorCodigo() {
        FuncionarioRepository repository = new FuncionarioRepository();

        Funcionario funcionario = new FuncionarioCLT(
                1, "Ana", "52998224725", 3000, 500
        );

        repository.salvar(funcionario);

        assertEquals(funcionario, repository.buscarPorCodigo(1));
    }

    @Test
    void deveRemoverFuncionario() {
        FuncionarioRepository repository = new FuncionarioRepository();

        Funcionario funcionario = new FuncionarioCLT(
                1, "Ana", "52998224725", 3000, 500
        );

        repository.salvar(funcionario);
        repository.remover(1);

        assertTrue(repository.listarTodos().isEmpty());
    }
}