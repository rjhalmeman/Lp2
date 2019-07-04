
package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {
    
    List<Trabalhador> lista = new ArrayList<>();

    public Controle() {
        Trabalhador t = new Trabalhador();
        t.setCpf("123");
        t.setNome("jao");
        t.setSalario(5000);
        adicionar(t);
        
        
        t = new Trabalhador("456", "Berola", 3252.97);
        adicionar(t);
        
        
        t = new Trabalhador("789", "Ana", 0.30);
        adicionar(t);
    }
    
    
    public void adicionar(Trabalhador trabalhador){
        lista.add(trabalhador);
    }
    
    public List<Trabalhador> listar(){
        return lista;
    }
    
   public Trabalhador buscar(String cpf){
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
