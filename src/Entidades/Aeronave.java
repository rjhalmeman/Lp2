package Entidades;

/**
 *
 * @author radames
 */
public class Aeronave {

    private String id;
    private String nome;
    private String foto;

    public Aeronave() {
    }

    public Aeronave(String id, String nome, String foto) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
    }

    //gets e sets
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return id + ";" + nome + ";" + foto;
    }
}//fim da classe

