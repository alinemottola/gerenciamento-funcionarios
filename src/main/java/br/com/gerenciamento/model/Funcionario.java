package br.com.gerenciamento.model;

public abstract class Funcionario implements Identificavel {

    private int codigo;
    private String nome;
    private String cpf;
    private double salarioBase;

    public Funcionario(int codigo, String nome, String cpf, double salarioBase) {

        if (!validarCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }

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

    private static boolean validarCpf(String cpf) {

        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;

        for (int i = 0; i < 9; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * (10 - i);
        }

        int resto = soma % 11;
        int primeiroDigito = resto < 2 ? 0 : 11 - resto;

        if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        soma = 0;

        for (int i = 0; i < 10; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * (11 - i);
        }

        resto = soma % 11;
        int segundoDigito = resto < 2 ? 0 : 11 - resto;

        return segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }
}