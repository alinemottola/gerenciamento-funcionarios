package br.com.gerenciamento.model;

public class FuncionarioCLT extends Funcionario implements Tributavel {

    private double beneficios;

    public FuncionarioCLT(int codigo, String nome, String cpf,
                          double salarioBase, double beneficios) {
        super(codigo, nome, cpf, salarioBase);
        if (beneficios < 0) {
            throw new IllegalArgumentException("Os benefícios não podem ser negativos.");
        }
        this.beneficios = beneficios;
    }

    public double getBeneficios() {
        return beneficios;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + beneficios;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.10;
    }

}