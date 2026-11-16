package br.com.gerenciamento.model;

public abstract class Funcionario implements Identificavel {

    private int codigo;
    private String nome;
    private String cpf;
    private double salarioBase;

    public Funcionario(int codigo, String nome, String cpf, double salarioBase) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

        if (salarioBase < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo.");
        }


        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public abstract double calcularRemuneracao();
}