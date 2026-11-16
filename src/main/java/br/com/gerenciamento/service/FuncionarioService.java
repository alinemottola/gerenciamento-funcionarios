package br.com.gerenciamento.service;

import br.com.gerenciamento.exception.FuncionarioJaCadastradoException;
import br.com.gerenciamento.exception.FuncionarioNaoEncontradoException;
import br.com.gerenciamento.model.Funcionario;
import br.com.gerenciamento.repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {

    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrarFuncionario(Funcionario funcionario) {

        if (funcionario == null) {
            throw new IllegalArgumentException(
                    "O funcionário não pode ser nulo.");
        }

        if (repository.buscarPorCodigo(funcionario.getCodigo()) != null) {
            throw new FuncionarioJaCadastradoException(
                    "Já existe um funcionário com esse código.");
        }

        repository.salvar(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }

    public Funcionario buscarFuncionario(int codigo) {

        Funcionario funcionario = repository.buscarPorCodigo(codigo);

        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException(
                    "Funcionário não encontrado.");
        }

        return funcionario;
    }

    public Funcionario buscarFuncionario(String cpf) {

        for (Funcionario funcionario : repository.listarTodos()) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }

        throw new FuncionarioNaoEncontradoException(
                "Funcionário com esse CPF não encontrado.");
    }

    public Funcionario buscarFuncionario(String nome, boolean buscaExata) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe um nome para a busca.");
        }

        for (Funcionario funcionario : repository.listarTodos()) {

            if (buscaExata) {
                if (funcionario.getNome().equalsIgnoreCase(nome)) {
                    return funcionario;
                }
            } else {
                if (funcionario.getNome().toLowerCase()
                        .contains(nome.toLowerCase())) {
                    return funcionario;
                }
            }
        }

        throw new FuncionarioNaoEncontradoException(
                "Funcionário com esse nome não encontrado.");
    }

    public void removerFuncionario(int codigo) {

        buscarFuncionario(codigo);

        repository.remover(codigo);
    }

    public double calcularTotalFolha() {

        double total = 0;

        for (Funcionario funcionario : repository.listarTodos()) {
            total += funcionario.calcularRemuneracao();
        }

        return total;
    }
}