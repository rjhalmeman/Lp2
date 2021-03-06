
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
    
    public void listar(){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
    }
    
   public Trabalhador buscar(String cpf){
       for (int i = 0; i < lista.size(); i++) {
           if (lista.get(i).getCpf().equals(cpf)) {
               return lista.get(i);
           }
       }
       return null;
       
   }
    
}
