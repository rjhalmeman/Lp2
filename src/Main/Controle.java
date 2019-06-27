
package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {
    
    List<Trabalhador> lista = new ArrayList<>();
    
    public void adicionar(Trabalhador trabalhador){
        lista.add(trabalhador);
    }
    
    public void listar(){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
    }
    
    
}
