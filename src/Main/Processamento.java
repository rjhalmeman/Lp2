package Main;

/**
 *
 * @author radames
 */
public class Processamento {

    private String categoria = "";

    public Processamento(int idade) {
        if (idade < 8) {
            categoria = "Infantil A";
        } else if (idade < 11) {
            categoria = "Infantil B";
        } else if (idade < 14) {
            categoria = "Juvenil A";
        } else if (idade < 18) {
            categoria = "Juvenil B";
        } else {
            categoria = "Adulto";
        }
    }

    public String getCategoria() {
        return categoria;
    }

}
