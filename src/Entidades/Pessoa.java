package Entidades;import java.util.Date;
import tools.CaixaDeFerramentas;/**
 *
 * @author belly
 */public class Pessoa {
private String id;private String nome;private Date datanascimento;private  boolean morto;private String ft;private String CorDosOlhos;public Pessoa() {
    }public Pessoa(String id,String nome,Date datanascimento,boolean morto,String ft,String CorDosOlhos) {
this.id=id;
this.nome=nome;
this.datanascimento=datanascimento;
this.morto=morto;
this.ft=ft;
this.CorDosOlhos=CorDosOlhos;
}

 //gets e sets
public String getId() {
        return id;
    }

public String getNome() {
        return nome;
    }

public Date getDatanascimento() {
        return datanascimento;
    }

public boolean isMorto() {
        return morto;
    }

public String getFt() {
        return ft;
    }

public String getCorDosOlhos() {
        return CorDosOlhos;
    }

public void setId(String id) {
this.id=id;
    }

public void setNome(String nome) {
this.nome=nome;
    }

public void setDatanascimento(Date datanascimento) {
this.datanascimento=datanascimento;
    }

public void setMorto(boolean morto) {
this.morto=morto;
    }

public void setFt( String ft) {
this.ft=ft;
    }

public void setCorDosOlhos( String CorDosOlhos) {
this.CorDosOlhos=CorDosOlhos;
    }

 @Override
    public String toString() {
CaixaDeFerramentas cf = new CaixaDeFerramentas();
 return  id+ ";"+ nome+ ";"+cf.converteDeDateParaString(datanascimento) + ";"+(morto?"Sim":"Não") +";"+ ft+ ";"+ CorDosOlhos;
}}//fim da classe

