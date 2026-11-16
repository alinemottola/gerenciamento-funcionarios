package br.com.gerenciamento.service;

import br.com.gerenciamento.exception.FuncionarioJaCadastradoException;
import br.com.gerenciamento.exception.FuncionarioNaoEncontradoException;
import br.com.gerenciamento.model.Funcionario;
import br.com.gerenciamento.model.FuncionarioCLT;
import br.com.gerenciamento.model.FuncionarioPJ;
import br.com.gerenciamento.repository.FuncionarioRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioServiceTest {

    private FuncionarioService criarService() {
        return new FuncionarioService(new FuncionarioRepository());
    }

    private Funcionario criarFuncionario() {
        return new FuncionarioCLT(
                1, "Ana", "52998224725", 3000, 500
        );
    }

    @Test
    void deveCadastrarFuncionario() {
        FuncionarioService service = criarService();

        service.cadastrarFuncionario(criarFuncionario());

        assertEquals(1, service.listarFuncionarios().size());
    }

    @Test
    void naoDeveCadastrarCodigoDuplicado() {
        FuncionarioService service = criarService();

        service.cadastrarFuncionario(criarFuncionario());

        Funcionario outro = new FuncionarioPJ(
                1, "Carlos", "12345678909", 1500, 2
        );

        assertThrows(FuncionarioJaCadastradoException.class,
                () -> service.cadastrarFuncionario(outro));
    }

    @Test
    void deveBuscarPorCodigo() {
        FuncionarioService service = criarService();
        service.cadastrarFuncionario(criarFuncionario());

        assertEquals("Ana", service.buscarFuncionario(1).getNome());
    }

    @Test
    void deveBuscarPorCpf() {
        FuncionarioService service = criarService();
        service.cadastrarFuncionario(criarFuncionario());

        assertEquals("Ana",
                service.buscarFuncionario("52998224725").getNome());
    }

    @Test
    void deveBuscarPorNomeExato() {
        FuncionarioService service = criarService();
        service.cadastrarFuncionario(criarFuncionario());

        assertEquals("Ana",
                service.buscarFuncionario("Ana", true).getNome());
    }

    @Test
    void deveBuscarPorParteDoNome() {
        FuncionarioService service = criarService();
        service.cadastrarFuncionario(criarFuncionario());

        assertEquals("Ana",
                service.buscarFuncionario("an", false).getNome());
    }

    @Test
    void deveRemoverFuncionario() {
        FuncionarioService service = criarService();
        service.cadastrarFuncionario(criarFuncionario());

        service.removerFuncionario(1);

        assertTrue(service.listarFuncionarios().isEmpty());
    }

    @Test
    void deveLancarErroAoBuscarInexistente() {
        FuncionarioService service = criarService();

        assertThrows(FuncionarioNaoEncontradoException.class,
                () -> service.buscarFuncionario(99));
    }

    @Test
    void deveLancarErroAoRemoverInexistente() {
        FuncionarioService service = criarService();

        assertThrows(FuncionarioNaoEncontradoException.class,
                () -> service.removerFuncionario(99));
    }

    @Test
    void deveCalcularTotalDaFolha() {
        FuncionarioService service = criarService();

        service.cadastrarFuncionario(criarFuncionario());

        service.cadastrarFuncionario(
                new FuncionarioPJ(
                        2, "Carlos", "12345678909", 1500, 2
                )
        );

        assertEquals(6500, service.calcularTotalFolha(), 0.01);
    }

    @Test
    void deveCalcularRemuneracaoComPolimorfismo() {
        Funcionario funcionario = criarFuncionario();

        assertEquals(3500, funcionario.calcularRemuneracao(), 0.01);
    }
}
