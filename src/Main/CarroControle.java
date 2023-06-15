package Main;

import MyUtil.CaixaDeFerramentas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class CarroControle {

    private List<Carro> listaCarro = new ArrayList<>();
    CaixaDeFerramentas cf = new CaixaDeFerramentas();

    public void inserir(Carro carro) {
        listaCarro.add(carro);
    }

    public Carro buscar(String placaCarro) {
        for (int i = 0; i < listaCarro.size(); i++) {
            if (listaCarro.get(i).getPlacaCarro().equals(placaCarro)) {
                return listaCarro.get(i);
            }
        }
        return null;
    }

    public void atualizar(Carro original, Carro modificado) {
        int qualOIndice = listaCarro.indexOf(original);
        listaCarro.set(qualOIndice, modificado);
    }

    public void excluir(Carro carro) {
        listaCarro.remove(carro);
    }

    public List<Carro> listar() {
        return listaCarro;
    }

    public List<String> listaString() {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < listaCarro.size(); i++) {
            ls.add(listaCarro.get(i).toString() + System.lineSeparator());
        }
        return ls;
    }

    public void preencherListaCarro(List<String> listaString) {
        for (String string : listaString) {
            String aux[] = string.split(";");
            Carro carro = new Carro();
            carro.setPlacaCarro(aux[0]);
            carro.setNomeCarro(aux[1]);
            carro.setPesoCarro(Double.valueOf(aux[2]));
            carro.setDataLancamento(cf.converteDeStringParaDate(aux[3]));
            carro.setCorDoCarro(Integer.valueOf(aux[4]));
            listaCarro.add(carro);           
        }
 
    }

}
