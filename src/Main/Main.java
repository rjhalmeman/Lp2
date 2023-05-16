/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import java.util.Date;
import java.util.List;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controle controle = new Controle();
        Pessoa pessoa = new Pessoa();

        pessoa.setCpf("111");
        pessoa.setNome("Berola");
        pessoa.setSobrenome("silva");
        pessoa.setDataNascimento(new Date());

        controle.inserir(pessoa);
        pessoa = new Pessoa("222", "Timocréia", "Soutier", new Date());
        controle.inserir(pessoa);
        pessoa = new Pessoa("3333", "Reduzina", "Soutier", new Date());
        controle.inserir(pessoa);

        List<Pessoa> lp = controle.listar();
        for (Pessoa p : lp) {
            System.out.println(p);
        }
        System.out.println("--------------------");
        String cpfDeQuem = "222";
        pessoa = controle.buscar(cpfDeQuem);
        if (pessoa == null) {
            System.out.println("Não está na lista");
        } else {
            System.out.println("Pessoa que escolhida " + pessoa);
        }
        System.out.println("----- alterar ----");
        Pessoa pessoaComDadosAlterados = new Pessoa(cpfDeQuem,"Timocréia Diogo","Soutier",new Date());
        controle.alterar(pessoa, pessoaComDadosAlterados);
        System.out.println("após alteração");
        lp = controle.listar();
        for (Pessoa p : lp) {
            System.out.println(p);
        }
        
        System.out.println("vai excluir");
        cpfDeQuem = "222";
        pessoa = controle.buscar(cpfDeQuem);
        controle.delete(pessoa);
         lp = controle.listar();
        for (Pessoa p : lp) {
            System.out.println(p);
        }
    }

}
