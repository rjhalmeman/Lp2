package Controles;import Entidades.Pessoa;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.CaixaDeFerramentas;/**
 *
 * @author belly
05/12/2022 - 15:32:59 */public class PessoaControle {
 private List<Pessoa> lista = new ArrayList<>(); public PessoaControle() {
    }public void limparLista() {
        lista.clear();//zera a lista
    } public void adicionar(Pessoa pessoa) {
        lista.add(pessoa);
    }public List<Pessoa> listar() {
        return lista;
    } public Pessoa buscar(String id) {
        for (int i = 0; i < lista.size(); i++) {
if (lista.get(i).getId().equals(id)) {
                return lista.get(i);
            }
        }
        return null;
    }public void alterar(Pessoa pessoa, Pessoa pessoaAntigo) {
        lista.set(lista.indexOf(pessoaAntigo), pessoa);

    }public void excluir(Pessoa pessoa) {
        lista.remove(pessoa);
    } public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Pessoa pessoa : lista) {
            listaDeString.add(pessoa.toString()+System.lineSeparator());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }public List<String> listStrings() {
        List<String> ls = new ArrayList<>();
        for (Pessoa t : lista) {
            ls.add(t.toString());
        }
        return ls;
    } public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        CaixaDeFerramentas cf = new CaixaDeFerramentas()
;        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Produto
        Pessoa pessoa;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
 pessoa = new Pessoa(aux[0],aux[1],cf.converteDeStringParaDate(aux[2]),Boolean.valueOf((aux[3].equals("Sim")?true:false)),(aux[4]),aux[5]);   lista.add(pessoa);
        }
}} //fim da classe