/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cadastropoo.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainOld {

    public static void main(String[] args) {
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        PessoaFisica pessoa1 = new PessoaFisica(1, "lucreio", "35412665478", 20);
        PessoaFisica pessoa2 = new PessoaFisica(2, "lucreia", "12345678900", 19);
        repo1.inserir(pessoa1);
        repo1.inserir(pessoa2);
        try {
            repo1.persistir("CPF.bin");
            System.out.println("CPFs Salvo com sucesso!");

        } catch (IOException ex) {
            Logger.getLogger(MainOld.class.getName()).log(Level.SEVERE, "Erro ao Salvar", ex);
        }

        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        try {
            System.out.println("CPFs Carregado com sucesso!");
            repo2.recuperar("CPF.bin");
            List<PessoaFisica> obterTodosCpf = repo2.obterTodos();
            for (PessoaFisica pessoaFisica : obterTodosCpf) {
                pessoaFisica.exibir();
            }           
        } catch (IOException ex) {
            Logger.getLogger(MainOld.class.getName()).log(Level.SEVERE, "Erro ao Carregar", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainOld.class.getName()).log(Level.SEVERE, "Erro ao Carregar", ex);
        }
        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
        PessoaJuridica pessoa3 = new PessoaJuridica(3, "SilasTurbo", "3214562312");
        PessoaJuridica pessoa4 = new PessoaJuridica(4, "AutoCores", "2314567891");
        repo3.inserir(pessoa3);
        repo3.inserir(pessoa4);
        try {
            System.out.println("CNPJs Salvo com sucesso!");
            repo3.persistir("CNPJS.bin");
        } catch (IOException ex) {
            Logger.getLogger(MainOld.class.getName()).log(Level.SEVERE, "Erro ao Salvar", ex);
        }
        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        try {
            repo4.recuperar("CNPJS.bin");
            System.out.println("CNPJs Carregado com sucesso!");
        } catch (IOException ex) {
            Logger.getLogger(MainOld.class.getName()).log(Level.SEVERE, "Erro ao Carregar", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainOld.class.getName()).log(Level.SEVERE, "Erro ao Carregar", ex);
        }
        List<PessoaJuridica> obterTodosCnpj = repo4.obterTodos();
        for (PessoaJuridica pessoaJuridica : obterTodosCnpj) {
            pessoaJuridica.exibir();
        }

    }
}
