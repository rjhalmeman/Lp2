package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.text.DecimalFormat;

class Saida {

   public void imprimirNumeroDouble(String msg,double num) {
        //formata e imprime um número double
        DecimalFormat df = new DecimalFormat("###,##0.00");
        System.out.println(msg + ": " + df.format(num));
    }

    public void rotuloString(String rotulo,String s) {
        System.out.println(rotulo+": "+s);
    }
}
