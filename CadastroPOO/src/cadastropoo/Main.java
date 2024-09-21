package CadastroPOO;

import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static PessoaFisicaRepo pessoaFisicaRepo = new PessoaFisicaRepo();
    private static PessoaJuridicaRepo pessoaJuridicaRepo = new PessoaJuridicaRepo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    alterar();
                    break;
                case 3:
                    excluir();
                    break;
                case 4:
                    obterPorId();
                    break;
                case 5:
                    obterTodos();
                    break;
                case 6:
                    salvarDados();
                    break;
                case 7:
                    recuperarDados();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Incluir");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Exibir pelo ID");
        System.out.println("5 - Exibir todos");
        System.out.println("6 - Salvar dados");
        System.out.println("7 - Recuperar dados");
        System.out.println("0 - Sair");
    }

    private static int lerOpcao() {
        int opcao = -1;
        while (opcao < 0 || opcao > 7) {
            System.out.print("Escolha uma opção: ");
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.next();
            }
        }
        return opcao;
    }

    private static void incluir() {
        System.out.println("Escolha o tipo: 1 - Física, 2 - Jurídica");
        int tipo = lerOpcao();
        scanner.nextLine();
        if (tipo == 1) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
            pessoaFisicaRepo.inserir(pf);
            System.out.println("Pessoa Física incluída com sucesso.");
        } else if (tipo == 2) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
            pessoaJuridicaRepo.inserir(pj);
            System.out.println("Pessoa Jurídica incluída com sucesso.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void alterar() {
        System.out.println("Escolha o tipo: 1 - Física, 2 - Jurídica");
        int tipo = lerOpcao();
        scanner.nextLine();
        if (tipo == 1) {
            System.out.print("ID da Pessoa Física a ser alterada: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            PessoaFisica pf = pessoaFisicaRepo.obter(id);
            if (pf != null) {
                System.out.println("Dados atuais: " + pf.getNome() + ", CPF: " + pf.getCpf() + ", Idade: " + pf.getIdade());
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Nova Idade: ");
                int idade = scanner.nextInt();
                pf.setNome(nome);
                pf.setCpf(cpf);
                pf.setIdade(idade);
                pessoaFisicaRepo.alterar(pf);
                System.out.println("Pessoa Física alterada com sucesso.");
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("ID da Pessoa Jurídica a ser alterada: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            PessoaJuridica pj = pessoaJuridicaRepo.obter(id);
            if (pj != null) {
                System.out.println("Dados atuais: " + pj.getNome() + ", CNPJ: " + pj.getCnpj());
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CNPJ: ");
                String cnpj = scanner.nextLine();
                pj.setNome(nome);
                pj.setCnpj(cnpj);
                pessoaJuridicaRepo.alterar(pj);
                System.out.println("Pessoa Jurídica alterada com sucesso.");
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void excluir() {
        System.out.println("Escolha o tipo: 1 - Física, 2 - Jurídica");
        int tipo = lerOpcao();
        if (tipo == 1) {
            System.out.print("ID da Pessoa Física a ser excluída: ");
            int id = scanner.nextInt();
            pessoaFisicaRepo.excluir(id);
            System.out.println("Pessoa Física excluída com sucesso.");
        } else if (tipo == 2) {
            System.out.print("ID da Pessoa Jurídica a ser excluída: ");
            int id = scanner.nextInt();
            pessoaJuridicaRepo.excluir(id);
            System.out.println("Pessoa Jurídica excluída com sucesso.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void obterPorId() {
        System.out.println("Escolha o tipo: 1 - Física, 2 - Jurídica");
        int tipo = lerOpcao();
        if (tipo == 1) {
            System.out.print("ID da Pessoa Física a ser exibida: ");
            int id = scanner.nextInt();
            PessoaFisica pf = pessoaFisicaRepo.obter(id);
            if (pf != null) {
                pf.exibir();
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("ID da Pessoa Jurídica a ser exibida: ");
            int id = scanner.nextInt();
            PessoaJuridica pj = pessoaJuridicaRepo.obter(id);
            if (pj != null) {
                pj.exibir();
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void obterTodos() {
        System.out.println("Escolha o tipo: 1 - Física, 2 - Jurídica");
        int tipo = lerOpcao();
        if (tipo == 1) {
            List<PessoaFisica> pessoasFisicas = pessoaFisicaRepo.obterTodos();
            if (!pessoasFisicas.isEmpty()) {
                for (PessoaFisica pf : pessoasFisicas) {
                    pf.exibir();
                }
            } else {
                System.out.println("Nenhuma Pessoa Física encontrada.");
            }
        } else if (tipo == 2) {
            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaRepo.obterTodos();
            if (!pessoasJuridicas.isEmpty()) {
                for (PessoaJuridica pj : pessoasJuridicas) {
                    pj.exibir();
                }
            } else {
                System.out.println("Nenhuma Pessoa Jurídica encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void salvarDados() {
        System.out.print("Digite o prefixo para os arquivos: ");
        String prefixo = scanner.next();
        try {
            pessoaFisicaRepo.persistir(prefixo + ".fisica.bin");
            pessoaJuridicaRepo.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void recuperarDados() {
        System.out.print("Digite o prefixo para os arquivos: ");
        String prefixo = scanner.next();
        try {
            pessoaFisicaRepo.recuperar(prefixo + ".fisica.bin");
            pessoaJuridicaRepo.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
