package Main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import tools.CaixaDeFerramentas;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.ManipulaArquivo;

/**
 *
 * @author Radames
 */
public class PessoaControle {

    private List<Pessoa> lista = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PessoaControle() {
    }

    public void adicionar(Pessoa pessoa) {
        lista.add(pessoa);
    }

    public List<Pessoa> listar() {
        return lista;
    }

    public Pessoa buscar(String cpf) {
        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getCpf().equals(cpf)) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Pessoa pessoa, Pessoa pessoaAntigo) {
        lista.set(lista.indexOf(pessoaAntigo), pessoa);

    }

    public void excluir(Pessoa pessoa) {
        lista.remove(pessoa);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Pessoa pessoa : lista) {
            listaDeString.add(pessoa.toString() + System.lineSeparator());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Carro
        Pessoa pessoa;
        CaixaDeFerramentas cdf = new CaixaDeFerramentas();
        for (String string : listaDeString) {
            String aux[] = string.split(";");

            try {
                pessoa = new Pessoa(
                        aux[0], aux[1], sdf.parse(aux[2]), Double.valueOf(aux[3]), Double.valueOf(aux[4])
                );
                lista.add(pessoa);
            } catch (ParseException ex) {
                Logger.getLogger(PessoaControle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}//fim da classe
