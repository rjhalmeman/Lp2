package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {

    List<Trabalhador> lista = new ArrayList<>();

    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 
        //facilitar os testes, quando fizermos a persistência em arquivo ele deverá ser excluído
        Trabalhador t = new Trabalhador();
        t.setCpf("123");
        t.setNome("Timocréia");
        t.setSalario(5000);
        t.setAposentado(false);
        adicionar(t);
        t = new Trabalhador("456", "Berola", 3252.97,true);
        adicionar(t);
        t = new Trabalhador("789", "Reduzina", 3000.30,false);
        adicionar(t);
        t = new Trabalhador("111", "Zulida", 3000.30,true);
        adicionar(t);
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

}
