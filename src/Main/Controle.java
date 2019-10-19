package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author radames
 */
public class Controle {

    private List<Trabalhador> lista = new ArrayList<>();

    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Trabalhador trabalhador) {
        lista.add(trabalhador);
    }

    public List<Trabalhador> listar() {
        return lista;
    }

    public Trabalhador buscar(String cpf) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCpf().equals(cpf)) {
                return lista.get(i);
            }
        }
        return null;

    }

    public void alterar(Trabalhador trabalhador, Trabalhador trabalhadorAntigo) {
        lista.set(lista.indexOf(trabalhadorAntigo), trabalhador);

    }

    public void excluir(Trabalhador trabalhador) {
        lista.remove(trabalhador);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Trabalhador trabalhador : lista) {
            listaDeString.add(trabalhador.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    void carregarDados(String caminho) {
        
        
       List<String> listaDeString = new  ManipulaArquivo().abrirArquivo(caminho);
       //converter de CSV para Trabalhador
        Trabalhador trabalhador;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            trabalhador = new Trabalhador(aux[0],aux[1],Double.valueOf(aux[2]),(aux[3].equals("Sim")?true:false));
            lista.add(trabalhador);            
        }
    }

}
