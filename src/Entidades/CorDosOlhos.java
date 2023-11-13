package Entidades;import java.util.Date;
import tools.CaixaDeFerramentas;/**
 *
 * @author belly
 */public class CorDosOlhos {
private  int id;private String cor;public CorDosOlhos() {
    }public CorDosOlhos(int id,String cor) {
this.id=id;
this.cor=cor;
}

 //gets e sets
public int getId() {
        return id;
    }

public String getCor() {
        return cor;
    }

public void setId(int id) {
this.id=id;
    }

public void setCor(String cor) {
this.cor=cor;
    }

 @Override
    public String toString() {
CaixaDeFerramentas cf = new CaixaDeFerramentas();
 return  id+ ";"+ cor;
}}//fim da classe

