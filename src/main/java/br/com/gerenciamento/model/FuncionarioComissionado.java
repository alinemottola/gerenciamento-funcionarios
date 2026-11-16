package br.com.gerenciamento.model;

public class FuncionarioComissionado extends Funcionario implements Tributavel{

    private double totalVendas;
    private double percentualComissao;

    public FuncionarioComissionado(int codigo, String nome, String cpf,
                                   double salarioBase, double totalVendas,
                                   double percentualComissao) {

        super(codigo, nome, cpf, salarioBase);

        if (totalVendas < 0) {
            throw new IllegalArgumentException(
                    "O total de vendas não pode ser negativo.");
        }

        if (percentualComissao < 0 || percentualComissao > 100) {
            throw new IllegalArgumentException(
                    "O percentual deve estar entre 0 e 100.");
        }

        this.totalVendas = totalVendas;
        this.percentualComissao = percentualComissao;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    @Override
    public double calcularRemuneracao() {
        double comissao = totalVendas * percentualComissao / 100;
        return getSalarioBase() + comissao;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.10;
    }
}
