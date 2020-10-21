package Controles;

import Entidades.Aeronave;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author radames 19/10/2020 - 11:20:39
 */
public class AeronaveControle {

    private List<Aeronave> lista = new ArrayList<>();

    public AeronaveControle() {
    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Aeronave aeronave) {
        lista.add(aeronave);
    }

    public List<Aeronave> listar() {
        return lista;
    }

    public Aeronave buscar(String id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Aeronave aeronave, Aeronave aeronaveAntigo) {
        lista.set(lista.indexOf(aeronaveAntigo), aeronave);

    }

    public void excluir(Aeronave aeronave) {
        lista.remove(aeronave);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Aeronave aeronave : lista) {
            listaDeString.add(aeronave.toString() + System.lineSeparator());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Aeronave
        Aeronave aeronave;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            aeronave = new Aeronave(aux[0], aux[1], aux[2]);
            lista.add(aeronave);
        }
    }
} //fim da classe
