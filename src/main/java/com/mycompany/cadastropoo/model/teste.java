/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cadastropoo.model;

import java.io.IOException;

/**
 *
 * @author felip
 */
public class teste {
       public static void main(String[] args){
        PessoaFisica pessoa1 = new PessoaFisica();
        PessoaFisica pessoa2 = new PessoaFisica();        
        PessoaFisicaRepo repositorio = new PessoaFisicaRepo();
        pessoa1.setId(1);
        pessoa1.setNome("João");
        pessoa1.setCpf("123456");
        pessoa1.setIdade(19);
        pessoa2.setId(2);
        pessoa2.setNome("pão");
        pessoa2.setCpf("789");
        pessoa2.setIdade(9);
        repositorio.inserir(pessoa1);
        repositorio.inserir(pessoa2);
        PessoaFisica mostrar = repositorio.obter(1);
        mostrar.exibir();
        try {
            System.out.println("Salvo com sucesso");
            repositorio.persistir("pessoasFisicas.bin");
        } catch (IOException e) {
        }

        try {
            repositorio.recuperar("pessoasFisicas.bin");
            System.out.println("Pessoas Físicas recuperadas:");
            repositorio.obterTodos().forEach(PessoaFisica::exibir);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
     }
}
   