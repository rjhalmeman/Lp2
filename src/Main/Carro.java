package Main;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author radames
 */
public class Carro {

    private String placaCarro;
    private String nomeCarro;
    private double pesoCarro;
    private Date dataLancamento;
    private int corDoCarro;
    private boolean temSeguro;

    public Carro() {
    }

    public Carro(String placaCarro, String nomeCarro, double pesoCarro, Date dataLancamento, int corDoCarro, boolean temSeguro) {
        this.placaCarro = placaCarro;
        this.nomeCarro = nomeCarro;
        this.pesoCarro = pesoCarro;
        this.dataLancamento = dataLancamento;
        this.corDoCarro = corDoCarro;
        this.temSeguro = temSeguro;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getNomeCarro() {
        return nomeCarro;
    }

    public void setNomeCarro(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }

    public double getPesoCarro() {
        return pesoCarro;
    }

    public void setPesoCarro(double pesoCarro) {
        this.pesoCarro = pesoCarro;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getCorDoCarro() {
        return corDoCarro;
    }

    public void setCorDoCarro(int corDoCarro) {
        this.corDoCarro = corDoCarro;
    }

    public boolean isTemSeguro() {
        return temSeguro;
    }

    public void setTemSeguro(boolean temSeguro) {
        this.temSeguro = temSeguro;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return placaCarro + ";" + nomeCarro + ";" + pesoCarro + ";" + sdf.format(dataLancamento) + ";" + corDoCarro+";"+(temSeguro?"Sim":"Não");
    }
}
