package Controle;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author radames
 */
public class GerarValidadorCPF {

    public GerarValidadorCPF(String nomeDaClasse, List<String> atributo, String caminho) {
        StringTools st = new StringTools();
        System.out.println("print __________________________________________________________");
        List<String> cg = new ArrayList();//código gerado
        cg.add("package ValidadorCPF;\n"
                + "/**\n"
                + " *\n"
                + " * @author gabriela\n"
                + " */\n"
                + "public class ValidadorCPF {\n"
                + "\n"
                + "    /**\n"
                + "     * @param args the command line arguments\n"
                + "     */\n"
                + "    public static boolean isCPF(String CPF) {\n"
                + "        // considera-se erro CPF's formados por uma sequencia de numeros iguais\n"
                + "        if (CPF.equals(\"00000000000\")\n"
                + "                || CPF.equals(\"11111111111\")\n"
                + "                || CPF.equals(\"22222222222\") || CPF.equals(\"33333333333\")\n"
                + "                || CPF.equals(\"44444444444\") || CPF.equals(\"55555555555\")\n"
                + "                || CPF.equals(\"66666666666\") || CPF.equals(\"77777777777\")\n"
                + "                || CPF.equals(\"88888888888\") || CPF.equals(\"99999999999\")\n"
                + "                || (CPF.length() != 11)) {\n"
                + "            return (false);\n"
                + "        }\n"
                + "\n"
                + "        char dig10, dig11;\n"
                + "        int sm, i, r, num, peso;\n"
                + ""
                + "try {\n"
                + "            // Calculo do 1o. Digito Verificador\n"
                + "            sm = 0;\n"
                + "            peso = 10;\n"
                + "            for (i = 0; i < 9; i++) {\n"
                + "                // converte o i-esimo caractere do CPF em um numero:\n"
                + "                // por exemplo, transforma o caractere '0' no inteiro 0\n"
                + "                // (48 eh a posicao de '0' na tabela ASCII)\n"
                + "                num = (int) (CPF.charAt(i) - 48);\n"
                + "                sm = sm + (num * peso);\n"
                + "                peso = peso - 1;\n"
                + "            }\n"
                + "\n"
                + "            r = 11 - (sm % 11);\n"
                + "            if ((r == 10) || (r == 11)) {\n"
                + "                dig10 = '0';\n"
                + "            } else {\n"
                + "                dig10 = (char) (r + 48); // converte no respectivo caractere numerico\n"
                + "            }\n"
                + "            // Calculo do 2o. Digito Verificador\n"
                + "            sm = 0;\n"
                + "            peso = 11;\n"
                + "            for (i = 0; i < 10; i++) {\n"
                + "                num = (int) (CPF.charAt(i) - 48);\n"
                + "                sm = sm + (num * peso);\n"
                + "                peso = peso - 1;\n"
                + "            }\n"
                + "\n"
                + "            r = 11 - (sm % 11);\n"
                + "            if ((r == 10) || (r == 11)) {\n"
                + "                dig11 = '0';\n"
                + "            } else {\n"
                + "                dig11 = (char) (r + 48);\n"
                + "            }\n"
                + "\n"
                + "            // Verifica se os digitos calculados conferem com os digitos informados.\n"
                + "            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {\n"
                + "                return (true);\n"
                + "            } else {\n"
                + "                return (false);\n"
                + "            }\n"
                + "        } catch (InputMismatchException erro) {\n"
                + "            return (false);\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public static String imprimeCPF(String CPF) {\n"
                + "        return (CPF.substring(0, 3) + \".\" + CPF.substring(3, 6) + \".\"\n"
                + "                + CPF.substring(6, 9) + \"-\" + CPF.substring(9, 11));\n"
                + "    }\n"
                + "}\n"
                + ""
                + "\n"
                + "}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho + "/src/ValidadorCPF/ValidadorCPF.java", cg);
        
        System.out.println("teste caminho =]");

    }

}
