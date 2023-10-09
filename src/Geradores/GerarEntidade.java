package Geradores;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarEntidade {

    public GerarEntidade(List<String> atributo, String nomeClasse, String caminhoProjetoDestino) {

        StringTools st = new StringTools();
        List<String> codigo = new ArrayList<>();
        String aux[];

        codigo.add("package Entidades;");
        codigo.add("import java.util.Date;");
        codigo.add("import java.text.SimpleDateFormat;");
        codigo.add("/**\n"
                + " *\n"
                + " * @author Radames\n"
                + " */");
        codigo.add("public class " + nomeClasse + " {");

        //gerar os atributos
        codigo.add(System.lineSeparator() + " //atributos da entidade" + System.lineSeparator());
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (aux[2].equals("Combobox")) {
                codigo.add("private " + aux[0] + " " + aux[1] + "; //será uma relação de 1:n (combobox)"+ System.lineSeparator());
            } else {
                codigo.add("private " + aux[0] + " " + aux[1] + ";"+ System.lineSeparator());
            }
        }
        codigo.add(System.lineSeparator() + System.lineSeparator() + "//métodos construtores" + System.lineSeparator());
        codigo.add("public " + nomeClasse + "() {\n"
                + "    }");

        String parametros = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            parametros += (aux[0] + " " + aux[1] + ",");
        }
        parametros = parametros.substring(0, parametros.length() - 1);
        codigo.add("public " + nomeClasse + "(" + parametros + "){");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            codigo.add("this." + aux[1] + "=" + aux[1] + ";");
        }
        codigo.add("}");
        codigo.add(System.lineSeparator() + System.lineSeparator() + "//métodos get" + System.lineSeparator());

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            codigo.add("public " + aux[0] + " get" + st.plMaiusc(aux[1]) + "() {");
            codigo.add("return " + aux[1] + ";");
            codigo.add("}");
        }

        codigo.add(System.lineSeparator() + System.lineSeparator() + "//métodos set" + System.lineSeparator());

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            codigo.add("public void set" + st.plMaiusc(aux[1]) + "( " + aux[0] + " " + aux[1] + ") {");
            codigo.add("this." + aux[1] + "=" + aux[1] + ";");
            codigo.add("}");
        }
        //toString
        codigo.add(System.lineSeparator() + " @Override" + System.lineSeparator());
        boolean umDate = false;

        parametros = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (aux[0].equals("Date")) {
                parametros += ("sdf.format(" + aux[1] + ")+\";\"+");
                umDate = true;
            } else {
                parametros += (aux[1] + "+\";\"+");
            }

        }
        parametros = parametros.substring(0, parametros.length() - 5);
        codigo.add("public String toString() {");
        if (umDate) {
            codigo.add("SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");");
        }
        codigo.add("return " + parametros + ";");
        codigo.add("}");

        codigo.add("}//fim da classe");

        //System.out.println("parametros "+parametros);
        //
        //mostra o código gerado
        //for (String c : codigo) {
        //  System.out.println(c);
        // }
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        String caminhoClasseEntidade = caminhoProjetoDestino + "/src/Entidades/" + nomeClasse + ".java";
        System.out.println("cce " + caminhoClasseEntidade);
        // System.exit(0);
        manipulaArquivo.salvarArquivo(caminhoClasseEntidade, codigo); //vai salvar a classe de entidade dentro da pasta entidades, no projeto destino.

    }

}
