package br.com.gerenciamento.repository;

import br.com.gerenciamento.model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    private List<Funcionario> funcionarios = new ArrayList<>();

    public void salvar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }

    public Funcionario buscarPorCodigo(int codigo) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCodigo() == codigo) {
                return funcionario;
            }
        }
        return null;
    }

    public void remover(int codigo) {
        funcionarios.removeIf(
                funcionario -> funcionario.getCodigo() == codigo
        );
    }
}