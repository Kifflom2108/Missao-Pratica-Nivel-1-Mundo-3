/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cadastropoo.model;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoCpf = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoCnpj = new PessoaJuridicaRepo();
        int opcao = getMenuOpcao(scanner, repoCpf, repoCnpj);
        while (opcao != 0);
    }

    private static void separador() {
        System.out.println("------------------------------------------------------------------");
    }

    private static int getMenuOpcao(Scanner scanner, PessoaFisicaRepo repoCpf, PessoaJuridicaRepo repoCnpj) {
        separador();
        System.out.println("1 - Incluir pessoa");
        System.out.println("2 - Alterar pessoa");
        System.out.println("3 - Excluir pessoa");
        System.out.println("4 - Exibir pelo id");
        System.out.println("5 - Exibir todos");
        System.out.println("6 - Salvar dados");
        System.out.println("7 - Carregar Dados");
        separador();
        int opcao = 0;
        try {
            opcao = scanner.nextInt();
        } catch (InputMismatchException e) {
            getLinhaFormatada(scanner, "Ocorreu um erro, tente novamente.");
            getMenuOpcao(scanner, repoCpf, repoCnpj);
        }

        switch (opcao) {
            case 1:
                incluirPessoa(scanner, repoCpf, repoCnpj);
                break;
            case 2:
                alterarPessoa(scanner, repoCpf, repoCnpj);
                break;
            case 3:
                excluirPessoa(scanner, repoCpf, repoCnpj);
                break;
            case 4:
                exibirPeloId(scanner, repoCpf, repoCnpj);
                break;
            case 5:
                exibirPessoa(scanner, repoCpf, repoCnpj);
                break;
            case 6:
                salvarDados(scanner, repoCpf, repoCnpj);
                break;
            case 7:
               carregarDados(scanner, repoCpf, repoCnpj);
                break;
            case 0:
                System.out.println("Saindo do programa.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;

        }
        return getMenuOpcao(scanner, repoCpf, repoCnpj);
    }

    private static void incluirPessoa(Scanner scanner, PessoaFisicaRepo repoCpf, PessoaJuridicaRepo repoCnpj) {
        int tipo = getIntFormatado(scanner, "Escolha o tipo de pessoa (1 para Física, 2 para Jurídica):");
        scanner.nextLine();

        if (tipo == 1) {
            String nome = getLinhaFormatada(scanner, "Digite o nome da pessoa física: ");
            String cpf = getLinhaFormatada(scanner, "Digite o CPF da Pessoa Física:");
            int idade = getIntFormatado(scanner, "Digite a idade da Pessoa Física:");
            PessoaFisica pessoaFisica = new PessoaFisica(repoCpf.obterTodos().size() + 1, nome, cpf, idade);
            repoCpf.inserir(pessoaFisica);
            System.out.println("Pessoa fisica adicionada com sucesso, o id é " + repoCpf.obterTodos().size());
        } else if (tipo == 2) {
            String nome = getLinhaFormatada(scanner, "Digite o nome da Pessoa Jurídica:");
            String cnpj = getLinhaFormatada(scanner, "Digite o CNPJ da Pessoa Jurídica:");
            PessoaJuridica pessoaJuridica = new PessoaJuridica(repoCnpj.obterTodos().size() + 1, nome, cnpj);
            repoCnpj.inserir(pessoaJuridica);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void alterarPessoa(Scanner scanner, PessoaFisicaRepo repoCpf, PessoaJuridicaRepo repoCnpj) {
        int tipo = getIntFormatado(scanner, "Escolha o tipo de pessoa (1 para Física, 2 para Jurídica):");
        if (tipo == 1) {
            int idPessoaFisicaAlterar = getIntFormatado(scanner, "Digite o ID da Pessoa Física que deseja alterar: ");
            PessoaFisica pessoaFisicaExistente = repoCpf.obter(idPessoaFisicaAlterar);
            if (pessoaFisicaExistente != null) {
                System.out.println("Dados da Pessoa Física a ser alterada: ");
                pessoaFisicaExistente.exibir();
                String novoNomePF = getLinhaFormatada(scanner, "Digite o novo nome: ");
                String novoCpf = getLinhaFormatada(scanner, "Digite o novo CPF: ");
                int novaIdade = getIntFormatado(scanner, "Digite a nova idade: ");
                PessoaFisica pessoaFisicaAtualizada = new PessoaFisica(idPessoaFisicaAlterar, novoNomePF, novoCpf, novaIdade);
                repoCpf.alterar(pessoaFisicaExistente, pessoaFisicaAtualizada);
                System.out.println("Pessoa Física alterada com sucesso! \n ");
            } else {
                System.out.println("Pessoa Física com o ID " + idPessoaFisicaAlterar + " não encontrado. \n");
            }
        } else if (tipo == 2) {
            int pessoaJuridicaAlterar = getIntFormatado(scanner, "Digite o ID da Pessoa Jurídica que deseja alterar: ");
            PessoaJuridica pessoaJuridicaExistente = repoCnpj.obter(pessoaJuridicaAlterar);
            if (pessoaJuridicaExistente != null) {
                System.out.println("Dados da Pessoa Jurídica a ser alterada");
                pessoaJuridicaExistente.exibir();
                String novoNomePj = getLinhaFormatada(scanner, "Digite o novo nome da Empresa: ");
                String novoCnpj = getLinhaFormatada(scanner, "Digite o novo CNPJ da Empresa: ");
                PessoaJuridica pessoaJuridicaAtualizada = new PessoaJuridica(pessoaJuridicaAlterar, novoNomePj, novoCnpj);
                repoCnpj.alterar(pessoaJuridicaExistente, pessoaJuridicaAtualizada);
            } else {
                System.out.println("Pessoa Jurídica com o ID " + pessoaJuridicaAlterar + " não encontrado.\n");
            }
        } else {
            System.out.println("Opção inválida.\n");
        }
    }

    private static void excluirPessoa(Scanner scanner, PessoaFisicaRepo repoCpf, PessoaJuridicaRepo repoCnpj) {

                    int exluir = getIntFormatado(scanner,"Escolha (1 - Pessoa Física, 2 - Pessoa Jurídica" );
                    if (exluir == 1) {
                        int pessoaFisicaExcluir = getIntFormatado(scanner, "Digite o ID da Pessoa Física que deseja excluir: ");
                        PessoaFisica pessoaFisicaExistente = repoCpf.obter(pessoaFisicaExcluir);
                        if (pessoaFisicaExistente != null) {
                            System.out.println("Dados da Pessoa Física a ser excluída");
                            pessoaFisicaExistente.exibir();
                            String confirmacaoPessoaFisica = getLinhaFormatada(scanner, "Tem certeza de que deseja excluir esta Pessoa Física? (S/N): ");
                            if (confirmacaoPessoaFisica.equalsIgnoreCase("S")) {
                                repoCpf.excluir(pessoaFisicaExcluir);
                                System.out.println("Pessoa Física excluída com sucesso! \n");
                            } else {
                                System.out.println("Exclusão cancelada. \n ");
                            }
                        } else {
                            System.out.println("Pessoa Física com o ID " + pessoaFisicaExcluir + " não encontrado. \n");
                        }
                    }
                    else if (exluir == 2) {
                        int pessoaJuridicaExcluir = getIntFormatado(scanner, "Digite o ID da Pessoa Jurídica que deseja excluir: ");
                        PessoaJuridica pessoaJuridicaExistente = repoCnpj.obter(pessoaJuridicaExcluir);
                        if (pessoaJuridicaExistente != null) {
                            System.out.print("Dados da Pessoa Jurídica a ser excluída");
                            pessoaJuridicaExistente.exibir();
                            String confirmacaoPessoaJuridica = getLinhaFormatada(scanner, "Tem certeza de que deseja excluir esta Pessoa Jurídica? (S/N): ");
                            if (confirmacaoPessoaJuridica.equalsIgnoreCase("S")) {
                                repoCnpj.excluir(pessoaJuridicaExcluir);
                                System.out.println("Pessoa Jurídica excluída com sucesso! \n");
                            } else {
                                System.out.println("Exclusão cancelada. \n");
                            }
                        } else {
                            System.out.println("Pessoa Jurídica com o ID " + pessoaJuridicaExcluir + " não encontrado. \n");
                        }
                    } else {
                        System.out.println("Opção inválida. \n");
                    }
    }

    private static void exibirPeloId(Scanner scanner, PessoaFisicaRepo repoCpf, PessoaJuridicaRepo repoCnpj) {
                    int obter = getIntFormatado(scanner, "Escolha (1 - Pessoa Física, 2 - Pessoa Jurídica)");
                    if (obter == 1) {
                        int obterPessoaFisica = getIntFormatado(scanner,"Digite o ID da Pessoa Física que deseja obter: ");
                        PessoaFisica pessoaFisicaExistente = repoCpf.obter(obterPessoaFisica);
                        if (pessoaFisicaExistente != null) {
                            System.out.println("Dados da Pessoa Física obtida");
                            pessoaFisicaExistente.exibir();
                        } else {
                            System.out.println("Pessoa Física com o ID " + obterPessoaFisica + " não encontrado. \n");
                        }
                    } else if (obter == 2) {
                        int obterPessoaJuridica = getIntFormatado(scanner,"Digite o ID da Pessoa Jurídica que deseja obter: ");
                        PessoaJuridica pessoaJuridicaExiste = repoCnpj.obter(obterPessoaJuridica);
                        if (pessoaJuridicaExiste != null) {
                            System.out.println("Dados da Pessoa Jurídica obtida");
                            pessoaJuridicaExiste.exibir();
                        }else {
                            System.out.println("Pessoa Jurídica com o ID " + obterPessoaJuridica + " não encontrado. \n");
                        }
                    } else {
                        System.out.println("Opção inválida. \n");
                    }
    }

    private static void exibirPessoa(Scanner scanner, PessoaFisicaRepo repoCpf, PessoaJuridicaRepo repoCnpj) {
        int tipo = getIntFormatado(scanner, "Escolha o tipo de pessoa (1 para Física, 2 para Jurídica):");
        scanner.nextLine();
        separador();

        if (tipo == 1) {
            List<PessoaFisica> obterTodosCpf = repoCpf.obterTodos();
            for (PessoaFisica pessoaFisica : obterTodosCpf) {
                pessoaFisica.exibir();
                separador();
            }
        } else if (tipo == 2) {
            List<PessoaJuridica> obterTodosCnpj = repoCnpj.obterTodos();
            for (PessoaJuridica pessoaJuridica : obterTodosCnpj) {
                pessoaJuridica.exibir();
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }
    private static void salvarDados(Scanner scanner, PessoaFisicaRepo repoCpf, PessoaJuridicaRepo repoCnpj){
        System.out.print("Digite o nome do arquivo: ");
                    String prefixoSalvar = scanner.next();
                    try {
                        repoCpf.persistir(prefixoSalvar + ".fisica.bin");
                        repoCnpj.persistir(prefixoSalvar + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar dados:" + e.getMessage());
                    }
    }
    
    private static void carregarDados(Scanner scanner, PessoaFisicaRepo repoCpf, PessoaJuridicaRepo repoCnpj){
        System.out.println("Digite o nome do arquivo: ");
                    String prefixoRecuperar = scanner.next();
                    try {
                        repoCpf.recuperar(prefixoRecuperar + ".fisica.bin");
                        repoCnpj.recuperar(prefixoRecuperar + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso. \n");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar dados: " + e.getMessage());
                    }
    }

    private static String getLinhaFormatada(Scanner scanner, String titulo) {
        System.out.println(titulo);
        return scanner.next();
    }

    private static int getIntFormatado(Scanner scanner, String titulo) {
        System.out.println(titulo);
        return scanner.nextInt();
    }
}
