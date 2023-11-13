package Controles;

import Entidades.CorDosOlhos;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.CaixaDeFerramentas;

/**
 *
 * @author belly 03/12/2022 - 10:34:11
 */
public class CorDosOlhosControle {

    private List<CorDosOlhos> lista = new ArrayList<>();

    public CorDosOlhosControle() {
    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(CorDosOlhos corDosOlhos) {
        lista.add(corDosOlhos);
    }

    public List<CorDosOlhos> listar() {
        return lista;
    }

    public CorDosOlhos buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(CorDosOlhos corDosOlhos, CorDosOlhos corDosOlhosAntigo) {
        lista.set(lista.indexOf(corDosOlhosAntigo), corDosOlhos);

    }

    public void excluir(CorDosOlhos corDosOlhos) {
        lista.remove(corDosOlhos);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (CorDosOlhos corDosOlhos : lista) {
            listaDeString.add(corDosOlhos.toString() + System.lineSeparator());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public List<String> listStrings() {
        List<String> ls = new ArrayList<>();
        for (CorDosOlhos t : lista) {
            ls.add(t.toString());
        }
        return ls;
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Produto
        CorDosOlhos corDosOlhos;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            corDosOlhos = new CorDosOlhos(Integer.valueOf(aux[0]), aux[1]);
            lista.add(corDosOlhos);
        }
    }
} //fim da classe
