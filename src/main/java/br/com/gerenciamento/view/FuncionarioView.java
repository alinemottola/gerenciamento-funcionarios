package br.com.gerenciamento.view;

import br.com.gerenciamento.model.*;
import br.com.gerenciamento.repository.FuncionarioRepository;
import br.com.gerenciamento.service.FuncionarioService;

import java.util.Scanner;

public class FuncionarioView {

    private final Scanner scanner = new Scanner(System.in);
    private final FuncionarioService service =
            new FuncionarioService(new FuncionarioRepository());

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAMENTO DE FUNCIONÁRIOS ===");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Buscar funcionário por código");
            System.out.println("4 - Remover funcionário");
            System.out.println("5 - Relatório de salários");
            System.out.println("6 - Total da folha de pagamento");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> buscar();
                    case 4 -> remover();
                    case 5 -> relatorio();
                    case 6 -> System.out.printf(
                            "Total da folha: R$ %.2f%n",
                            service.calcularTotalFolha());
                    case 0 -> System.out.println("Encerrando sistema...");
                    default -> System.out.println("Opção inválida.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                opcao = -1;
            }

        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.println("\nTipo de funcionário:");
        System.out.println("1 - CLT");
        System.out.println("2 - PJ");
        System.out.println("3 - Comissionado");

        int tipo = lerInteiro("Tipo: ");

        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido.");
            return;
        }

        int codigo = lerInteiro("Código: ");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Funcionario funcionario;

        switch (tipo) {
            case 1 -> {
                double salario = lerDouble("Salário-base: ");
                double beneficios = lerDouble("Benefícios: ");

                funcionario = new FuncionarioCLT(
                        codigo, nome, cpf, salario, beneficios);
            }
            case 2 -> {
                double valor = lerDouble("Valor por projeto: ");
                int quantidade = lerInteiro("Quantidade de projetos: ");

                funcionario = new FuncionarioPJ(
                        codigo, nome, cpf, valor, quantidade);
            }
            default -> {
                double salario = lerDouble("Salário-base: ");
                double vendas = lerDouble("Total de vendas: ");
                double percentual = lerDouble("Comissão (%): ");

                funcionario = new FuncionarioComissionado(
                        codigo, nome, cpf, salario, vendas, percentual);
            }
        }

        service.cadastrarFuncionario(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void listar() {
        if (service.listarFuncionarios().isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario funcionario : service.listarFuncionarios()) {
            mostrarFuncionario(funcionario);
        }
    }

    private void buscar() {
        int codigo = lerInteiro("Código do funcionário: ");
        mostrarFuncionario(service.buscarFuncionario(codigo));
    }

    private void remover() {
        int codigo = lerInteiro("Código para remover: ");
        service.removerFuncionario(codigo);
        System.out.println("Funcionário removido com sucesso!");
    }

    private void relatorio() {
        System.out.println("\n=== RELATÓRIO DE SALÁRIOS ===");

        listar();

        System.out.printf("TOTAL: R$ %.2f%n",
                service.calcularTotalFolha());
    }

    private void mostrarFuncionario(Funcionario funcionario) {

        System.out.println("----------------------------");
        System.out.println("Código: " + funcionario.getCodigo());
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("CPF: " + funcionario.getCpf());

        if (funcionario instanceof FuncionarioCLT clt) {

            System.out.println("Categoria: CLT");
            System.out.printf("Salário-base: R$ %.2f%n",
                    clt.getSalarioBase());
            System.out.printf("Benefícios: R$ %.2f%n",
                    clt.getBeneficios());

        } else if (funcionario instanceof FuncionarioPJ pj) {

            System.out.println("Categoria: PJ");
            System.out.printf("Valor por projeto: R$ %.2f%n",
                    pj.getValorPorProjeto());
            System.out.println("Quantidade de projetos: " +
                    pj.getQuantidadeProjetos());

        } else if (funcionario instanceof FuncionarioComissionado comissionado) {

            System.out.println("Categoria: Comissionado");
            System.out.printf("Salário-base: R$ %.2f%n",
                    comissionado.getSalarioBase());
            System.out.printf("Total de vendas: R$ %.2f%n",
                    comissionado.getTotalVendas());
            System.out.printf("Percentual de comissão: %.2f%%%n",
                    comissionado.getPercentualComissao());
        }

        System.out.printf("Remuneração: R$ %.2f%n",
                funcionario.calcularRemuneracao());

        if (funcionario instanceof Tributavel tributavel) {
            System.out.printf("Tributos: R$ %.2f%n",
                    tributavel.calcularTributos());
        }
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(scanner.nextLine());
    }

    private double lerDouble(String mensagem) {
        System.out.print(mensagem);
        return Double.parseDouble(
                scanner.nextLine().replace(",", "."));
    }
}
