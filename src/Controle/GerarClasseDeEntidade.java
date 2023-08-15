package Controle;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarClasseDeEntidade {

    public GerarClasseDeEntidade(String nomeDaClasse, List<String> atributo,String caminho) {
        StringTools st = new StringTools();
        List<String> cg = new ArrayList();//código gerado
        cg.add("package Entidades;");
        cg.add("import java.util.Date;");
        cg.add("/**\n"
                + " *\n"
                + " * @author radames\n"
                + " */");
        cg.add("public class " + nomeDaClasse + " {\n");
        String[] aux;
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("private " + aux[0] + " " + aux[1] + ";");
        }
        cg.add("public " + nomeDaClasse + "() {\n"
                + "    }");

        String s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            s = s + aux[0] + " " + aux[1] + ",";
        }
        s = s.substring(0, s.length() - 1);
        cg.add("public " + nomeDaClasse + "(" + s + ") {\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("this." + aux[1] + "=" + aux[1] + ";\n");
        }
        cg.add("}");

        cg.add("\n\n //gets e sets\n");

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("public " + aux[0] + " get" + st.plMaiusc(aux[1]) + "() {\n"
                    + "        return " + aux[1] + ";\n"
                    + "    }\n\n");
        }

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("public void" + " set" + st.plMaiusc(aux[1])
                    + "(" + aux[0] + " " + aux[1] + ") {\n"
                    + "this." + aux[1] + "=" + aux[1] + ";\n"
                    + "    }\n\n");
        }

        cg.add(" @Override\n"
                + "    public String toString() {\n return");

        s = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            s = s + " " + aux[1] + "+ \";\"+";
        }
        s = s.substring(0, s.length() - 6);
        cg.add(s + ";");
        cg.add("\n}");

        cg.add("}//fim da classe\n\n");

//        for (String linha : cg) {
//            System.out.println(linha);
//        }
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho+"/src/Entidades/" + nomeDaClasse + ".java", cg);

    }

}
