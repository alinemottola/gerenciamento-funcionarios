package br.com.gerenciamento.model;

public class FuncionarioPJ extends Funcionario {

    private double valorPorProjeto;
    private int quantidadeProjetos;

    public FuncionarioPJ(int codigo, String nome, String cpf,
                         double valorPorProjeto, int quantidadeProjetos) {

        super(codigo, nome, cpf, 0);

        if (valorPorProjeto < 0 || quantidadeProjetos < 0) {
            throw new IllegalArgumentException(
                    "Os valores dos projetos não podem ser negativos.");
        }

        this.valorPorProjeto = valorPorProjeto;
        this.quantidadeProjetos = quantidadeProjetos;
    }

    public double getValorPorProjeto() {
        return valorPorProjeto;
    }

    public int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

    @Override
    public double calcularRemuneracao() {
        return valorPorProjeto * quantidadeProjetos;
    }
}
