package daos;

import entidades.TipoConta;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class ControleTipoConta {

    private List<TipoConta> lista = new ArrayList<>();

    public ControleTipoConta() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public int autoIdTipoConta() {
        if (lista.isEmpty()) {
            return 1;
        } else {
            return lista.get(lista.size()-1).getIdTipoConta() + 1;
        }
    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(TipoConta tipoConta) {
        lista.add(tipoConta);
    }

    public List<TipoConta> listar() {
        return lista;
    }

    public TipoConta buscar(int idTipoConta) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdTipoConta() == idTipoConta) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(TipoConta tipoConta, TipoConta tipoContaAntigo) {
        lista.set(lista.indexOf(tipoContaAntigo), tipoConta);

    }

    public void excluir(TipoConta tipoConta) {
        lista.remove(tipoConta);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (TipoConta tipoConta : lista) {
            listaDeString.add(tipoConta.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para TipoConta
        TipoConta tipoConta;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            tipoConta = new TipoConta(Integer.valueOf(aux[0]), aux[1]);
            lista.add(tipoConta);
        }
    }

}
